package me.emig.engineEmi.module

import MyDependency
import com.soywiz.korev.MouseEvent
import com.soywiz.korev.addEventListener
import com.soywiz.korev.mouse
import com.soywiz.korge.box2d.worldView
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onDown
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.delay
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.VfsFile
import me.emig.engineEmi.Controller
import me.emig.engineEmi.engine
import me.emig.engineEmi.input.Keyboard
import me.emig.engineEmi.registerBodyWithWorld
import me.emig.engineEmi.screenElements.ScreenElement
import me.emig.engineEmi.screenElements.bodies.Ebody
import me.emig.engineEmi.screenElements.canvasElements.CanvasElement

open class SceneTemplate(val myDependency: MyDependency) : Scene() {

    open val viewWillLoad: suspend () -> Unit = {}

    open val viewDidLoad: suspend () -> Unit = {}

    var canvasElements = mutableListOf<CanvasElement>()
    var bodies = mutableListOf<Ebody>()
    val allScreenElements: List<ScreenElement>
        get() {
            return canvasElements.plus(bodies).map { it }
        }
    var controllers = mutableListOf<Controller>()
    var map: VfsFile? = null
    var camera = Camera()

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

            engine.canvasElements.forEach {
                println(it)
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

        mouse {
            onDown { }

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
}
