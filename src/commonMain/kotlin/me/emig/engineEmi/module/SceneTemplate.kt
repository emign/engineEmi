package me.emig.engineEmi.module

import com.soywiz.korev.MouseEvent
import com.soywiz.korev.addEventListener
import com.soywiz.korge.box2d.worldView
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.delay
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs
import me.emig.engineEmi.Controller
import me.emig.engineEmi.engine
import me.emig.engineEmi.input.Keyboard
import me.emig.engineEmi.registerBodyWithWorld
import me.emig.engineEmi.screenElements.ScreenElement
import me.emig.engineEmi.screenElements.bodies.Ebody
import me.emig.engineEmi.screenElements.canvasElements.CanvasElement
import kotlin.reflect.KClass

open class SceneTemplate : Scene(), Controller {

    open val viewWillLoad: suspend () -> Unit = {}

    open val viewDidLoad: suspend () -> Unit = {}

    open var canvasElements = mutableListOf<CanvasElement>()
    open var bodies = mutableListOf<Ebody>()
    open val allScreenElements: List<ScreenElement>
        get() {
            return canvasElements.plus(bodies).map { it }
        }
    open var controllers = mutableListOf<Controller>()
    open var map: VfsFile? = null
    open var camera = Camera()

    override suspend fun Container.sceneInit() {


        views.clearColor = Colors.WHITE
        viewWillLoad()

        camera = camera {
            map?.let { tiledMapView(it.readTiledMap()) }

            worldView {
                position(engine.view.width / 2, engine.view.height / 2).scale(engine.view.scale)
                if (bodies.isNotEmpty()) {
                    bodies.run {
                        map { registerBodyWithWorld(it) }
                        map { it.body }
                    }
                }
            }


            // CANVAS
            if (canvasElements.isNotEmpty()) {
                canvasElements.run {
                    map { it.prepareElement() }
                    map { addChild(it) }
                }
                launch {
                    while (true) {

                        canvasElements.onEach { it.onEveryFrame() }
                        delay(engine.delay)
                    }
                }
            }
        }

        // GLOBAL (CANVAS AND BOX2D)
        addEventListener<MouseEvent> { controllers.onEach { element -> element.reactToMouseEvent(it) } }

        keys {
            onKeyDown { Keyboard.keyDown(it.key); controllers.onEach { element -> element.reactToKeyEvent(it) } }
            onKeyUp { Keyboard.keyReleased(it.key); controllers.onEach { element -> element.reactToKeyEvent(it) } }
        }

        engine.viewDidLoadBody()
    }


    /**
     * Registriert ein [CanvasElement] bei der Engine (reguläre Objekte)
     * @param canvasElement CanvasElement
     */
    fun registerCanvasElement(canvasElement: CanvasElement) {
        canvasElements.add(canvasElement)
    }

    /**
     * Registriert einen [Ebody] bei der Engine (Physikobjekte)
     * @param body Ebody
     */

    fun registerBody(body: Ebody) {
        bodies.add(body)
    }

    /**
     * Registriert einen [Controller] bei der Engine
     * @param controller Controller
     */
    fun registerController(controller: Controller) {
        controllers.add(controller)
    }

    /**
     * Registriert eine Map bei der Engine
     * @param pathToMap String zum Pfad der Tiledmap (im Resources Ordner)
     */
    fun registerMap(pathToMap: String) {
        map = resourcesVfs[pathToMap]
    }

    /**
     * Registriert einen [Ebody] oder ein [CanvasElement] bei der Engine
     * Es ist auch möglich Arrays und Collections zu registrieren.
     * [Ebody] und [CanvasElement] dürfen in den Arrays oder Collections nicht gemischt vorkommen
     * @param o Any
     */
    fun register(o: Any) {
        if (o is Array<*>)
            o.map { it?.let { register(it) } }
        if (o is Collection<*>)
            o.map { it?.let { register(it) } }
        if (o is Ebody)
            registerBody(o)
        if (o is CanvasElement)
            registerCanvasElement(o)
        if (o is Controller)
            registerController(o)
    }

    inline fun <reified T : Scene> changeSceneTo(clazz: KClass<T>) {
        sceneContainer.changeToAsync<T>()
    }

    inline fun <reified T : Scene> changeSceneTo() {
        sceneContainer.changeToAsync<T>()
    }


}
