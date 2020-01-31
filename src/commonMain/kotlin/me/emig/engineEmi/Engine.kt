package me.emig.engineEmi

import com.soywiz.klock.milliseconds
import com.soywiz.korev.MouseEvent
import com.soywiz.korev.addEventListener
import com.soywiz.korev.keys
import com.soywiz.korev.mouse
import com.soywiz.korge.Korge
import com.soywiz.korge.box2d.WorldView
import com.soywiz.korge.box2d.worldView
import com.soywiz.korge.input.onDown
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.input.onKeyUp
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.Camera
import com.soywiz.korge.view.camera
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.delay
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs
import me.emig.engineEmi.input.Keyboard
import me.emig.engineEmi.screenElements.ScreenElement
import me.emig.engineEmi.screenElements.bodies.Ebody
import me.emig.engineEmi.screenElements.canvasElements.CanvasElement


val engine = Engine()
/**
 * Die Game-Engine. Sie ist ein Singleton und wird mit [Engine.run] gestartet.
 * @property canvasElements Alle registrieten Objekte vom Typ [CanvasElement]
 * @property bodies Alle registrieten Objekte des Typs [Ebody]
 *
 */
class Engine {
    var canvasElements = mutableListOf<CanvasElement>()
    var bodies = mutableListOf<Ebody>()
    val allScreenElements: List<ScreenElement>
        get() {
            return canvasElements.plus(bodies).map { it }
        }
    var controllers = mutableListOf<Controller>()
    var view = ViewWindow()
    var viewWillLoadBody: suspend () -> Unit = {}
    var viewDidLoadBody: suspend () -> Unit = {}
    var title = "Engine Emi"
    var delay = 16.milliseconds
    var camera = Camera()
    var map: VfsFile? = null

    fun init(initBody: () -> Unit) = this.apply {
        view.width = 1280
        view.height = 720
        view.scale = 1
        initBody()
    }

    suspend fun start() =
        Korge(
            quality = GameWindow.Quality.PERFORMANCE,
            title = title,
            width = view.width,
            height = view.height
        ) {

            views.clearColor = Colors.WHITE
            viewWillLoadBody()


            camera = camera {
                map?.let { tiledMapView(it.readTiledMap()) }

                worldView {
                    position(view.width / 2, view.height / 2).scale(view.scale)
                    if (bodies.isNotEmpty()) {
                        bodies.run {
                            map { registerBodyWithWorld(it) }
                            map { it.body }
                        }
                    }
                }

                // CANVAS
                if (!canvasElements.isEmpty()) {

                    canvasElements.run {
                        map { it.prepareElement() }
                        map { addChild(it) }
                    }
                    launch {
                        while (true) {
                            canvasElements.onEach { it.onEveryFrame() }
                            delay(delay)
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

            mouse {
                onDown { }
            }
            viewDidLoadBody()
        }

    fun viewWillLoad(viewWillLoadBody: suspend () -> Unit = {}) {
        this.viewWillLoadBody = viewWillLoadBody
    }

    fun viewDidLoad(viewDidLoadBody: suspend () -> Unit = {}) {
        this.viewDidLoadBody = viewDidLoadBody
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
        this.map = resourcesVfs[pathToMap]
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
}

class ViewWindow {
    var height = 0
    var width = 0
    var scale = 0
}

suspend fun WorldView.registerBodyWithWorld(body: Ebody) {
    body.initForWorld(this.world)
}