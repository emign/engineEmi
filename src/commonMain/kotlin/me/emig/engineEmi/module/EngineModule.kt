import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import me.emig.engineEmi.engine
import me.emig.engineEmi.module.SceneTemplate
import me.emig.engineEmi.screenElements.canvasElements.Rechteck
import kotlin.reflect.KClass

object EngineModule : Module() {

    val scene1 = MyScene1()
    val scene2 = MyScene2()

    override val mainScene: KClass<out Scene> = DefaultScene::class

    override suspend fun AsyncInjector.configure() {
        engine.registerScene(DefaultScene())
        mapInstance(MyDependency("HELLO WORLD"))
        val test = SceneTemplate(MyDependency("HELLO WORLD"))::class

        mapPrototype { DefaultScene() }
        //  mapPrototype { MyScene1(get()) }
        // mapPrototype { MyScene2(get()) }


        mapPrototype { scene1 }
        mapPrototype { scene2 }
        //mapInstance<MyScene2>(scene2)
        //mapInstance<MyScene1>(scene1)
    }
}

class MyDependency(val value: String)

class MyScene1 : SceneTemplate() {
    override val viewWillLoad: suspend () -> Unit = {
        registerCanvasElement(Rechteck(100, 100, 300, 300, Colors.GREEN))
    }
}

class MyScene2 : SceneTemplate() {
    override val viewWillLoad: suspend () -> Unit = {
        registerCanvasElement(Rechteck(100, 100, 300, 300, Colors.RED))
    }
}

