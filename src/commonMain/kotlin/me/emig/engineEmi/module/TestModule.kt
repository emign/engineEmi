import com.soywiz.korev.KeyEvent
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import me.emig.engineEmi.Controller
import me.emig.engineEmi.module.SceneTemplate
import me.emig.engineEmi.screenElements.canvasElements.Rechteck
import kotlin.reflect.KClass

object TestModule : Module() {
    override val mainScene: KClass<out Scene> = DefaultScene::class

    override suspend fun AsyncInjector.configure() {
        mapInstance(MyDependency("HELLO WORLD"))
        val test = MyScene1::class

        mapPrototype { DefaultScene(get()) }
        mapPrototype { MyScene1(get()) }
        mapPrototype { MyScene2(get()) }

        // Add new Scenes here with:
        // mapPrototype {CLASSNAME(get()}
    }
}

class MyDependency(val value: String)

class MyScene1(myDependency: MyDependency) : SceneTemplate(myDependency) {
    override val viewWillLoad: suspend () -> Unit = {
        registerCanvasElement(Rechteck(100, 100, 300, 300, Colors.GREEN))
    }

}

class MyScene2(myDependency: MyDependency) : SceneTemplate(myDependency) {
    override val viewWillLoad: suspend () -> Unit = {
        registerCanvasElement(Rechteck(100, 100, 300, 300, Colors.RED))
    }
}

class SceneController : Controller {

    override fun reactToKeyEvent(event: KeyEvent) {

    }
}