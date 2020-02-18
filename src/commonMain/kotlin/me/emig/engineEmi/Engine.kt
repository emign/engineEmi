package me.emig.engineEmi


import DefaultScene
import com.soywiz.klock.milliseconds
import com.soywiz.korge.Korge
import com.soywiz.korge.box2d.WorldView
import com.soywiz.korge.view.Camera
import com.soywiz.korge.view.Stage
import com.soywiz.korgw.GameWindow
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.SizeInt
import me.emig.engineEmi.module.DefaultModule
import me.emig.engineEmi.module.EngineModuleDependency
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

    var view = ViewWindow()
    var viewWillLoadBody: suspend () -> Unit = {}
    var viewDidLoadBody: suspend () -> Unit = {}
    var title = "Engine Emi"
    var delay = 16.milliseconds
    var camera = Camera()
    var map: VfsFile? = null
    lateinit var stage: Stage

    val scene = DefaultScene(
        camera = camera,
        viewWillLoadBody = viewWillLoadBody,
        viewDidLoadBody = viewDidLoadBody,
        myDependency = EngineModuleDependency(title)
    )

    val scenes = mutableListOf(scene)
    var activeScene = scene


    fun init(initBody: () -> Unit) = this.apply {
        view.width = 1280
        view.height = 720
        view.scale = 100
        initBody()
    }

    suspend fun start() = Korge(
        Korge.Config(
            module = DefaultModule(
                quality = GameWindow.Quality.QUALITY,
                size = SizeInt.invoke(view.width, view.height),
                title = title,
                camera = camera,
                viewWillLoadBody = viewWillLoadBody,
                viewDidLoadBody = viewDidLoadBody
            )
        )
    )

    fun register(scene: DefaultScene) {
        scenes.add(scene)
    }

    fun viewWillLoad(viewWillLoadBody: suspend () -> Unit = {}) {
        scene.viewWillLoadBody = viewWillLoadBody
    }

    fun viewDidLoad(viewDidLoadBody: suspend () -> Unit = {}) {
        scene.viewDidLoadBody = viewDidLoadBody
    }

    /**
     * Registriert ein [CanvasElement] bei der Engine (reguläre Objekte)
     * @param canvasElement CanvasElement
     */
    fun registerCanvasElement(canvasElement: CanvasElement, scene: DefaultScene = engine.scene) {
        scene.canvasElements.add(canvasElement)
    }

    /**
     * Registriert einen [Ebody] bei der Engine (Physikobjekte)
     * @param body Ebody
     */
    fun registerBody(body: Ebody, scene: DefaultScene = engine.scene) {
        scene.bodies.add(body)
    }

    /**
     * Registriert einen [Controller] bei der Engine
     * @param controller Controller
     */
    fun registerController(controller: Controller, scene: DefaultScene = engine.scene) {
        scene.controllers.add(controller)
    }

    /**
     * Registriert eine Map bei der Engine
     * @param pathToMap String zum Pfad der Tiledmap (im Resources Ordner)
     */
    fun registerMap(pathToMap: String, scene: DefaultScene = engine.scene) {
        scene.map = resourcesVfs[pathToMap]
    }

    /**
     * Registriert einen [Ebody] oder ein [CanvasElement] bei der Engine
     * Es ist auch möglich Arrays und Collections zu registrieren.
     * [Ebody] und [CanvasElement] dürfen in den Arrays oder Collections nicht gemischt vorkommen
     * @param o Any
     */
    fun register(o: Any, scene: DefaultScene = engine.scene) {
        if (o is Array<*>)
            o.map { it?.let { register(it) } }
        if (o is Collection<*>)
            o.map { it?.let { register(it) } }
        if (o is Ebody)
            registerBody(o, scene)
        if (o is CanvasElement)
            registerCanvasElement(o, scene)
        if (o is Controller)
            registerController(o, scene)
    }

    fun switchSceneTo(targetScene: DefaultScene) {
        scene.sceneContainer.launchImmediately {
            scene.sceneContainer.changeTo(targetScene::class)
        }
        activeScene = targetScene
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