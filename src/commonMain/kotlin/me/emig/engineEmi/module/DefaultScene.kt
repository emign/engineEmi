import com.soywiz.korev.MouseEvent
import com.soywiz.korev.addEventListener
import com.soywiz.korev.mouse
import com.soywiz.korge.box2d.worldView
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onDown
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.camera
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.delay
import com.soywiz.korio.async.launch
import com.soywiz.korio.async.launchImmediately
import me.emig.engineEmi.engine
import me.emig.engineEmi.input.Keyboard
import me.emig.engineEmi.module.SceneTemplate
import me.emig.engineEmi.registerBodyWithWorld

open class DefaultScene(dependency: MyDependency) : SceneTemplate(dependency) {

    override suspend fun Container.sceneInit() {

        views.clearColor = Colors.WHITE
        engine.viewWillLoadBody()

        engine.camera = camera {
            engine.map?.let { tiledMapView(it.readTiledMap()) }

            worldView {
                position(engine.view.width / 2, engine.view.height / 2).scale(engine.view.scale)
                if (engine.bodies.isNotEmpty()) {
                    engine.bodies.run {
                        map { registerBodyWithWorld(it) }
                        map { it.body }
                    }
                }
            }

            engine.canvasElements.forEach {
                println(it)
            }

            // CANVAS
            if (engine.canvasElements.isNotEmpty()) {
                engine.canvasElements.run {
                    map { it.prepareElement() }
                    map { addChild(it) }
                }
                launch {
                    while (true) {
                        engine.canvasElements.onEach { it.onEveryFrame() }
                        delay(engine.delay)
                    }
                }
            }
        }

        // GLOBAL (CANVAS AND BOX2D)
        addEventListener<MouseEvent> { engine.controllers.onEach { element -> element.reactToMouseEvent(it) } }

        keys {
            onKeyDown { Keyboard.keyDown(it.key); engine.controllers.onEach { element -> element.reactToKeyEvent(it) } }
            onKeyUp { Keyboard.keyReleased(it.key); engine.controllers.onEach { element -> element.reactToKeyEvent(it) } }
        }

        mouse {
            onDown { }
        }
        onClick {
            launchImmediately {
                println("CLICKED")
                sceneContainer.changeTo<MyScene1>()
            }
        }

        engine.viewDidLoadBody()

    }
}

