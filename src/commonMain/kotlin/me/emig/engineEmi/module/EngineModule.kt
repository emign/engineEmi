package me.emig.engineEmi.module

import com.soywiz.korev.KeyEvent
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import me.emig.engineEmi.engine
import me.emig.engineEmi.screenElements.canvasElements.Rechteck
import kotlin.reflect.KClass

object EngineModule : Module() {
    override val mainScene: KClass<out Scene> = DefaultScene::class

    override val size: SizeInt
        get() = SizeInt(engine.view.width, engine.view.height)

    override val windowSize: SizeInt
        get() = SizeInt(engine.view.width, engine.view.height)


    override suspend fun AsyncInjector.configure() {


        mapPrototype { DefaultScene() }

        mapPrototype { MyScene1() }
        mapPrototype { MyScene2() }

    }
}

class MyScene1 : SceneTemplate() {
    override val viewWillLoad: suspend () -> Unit = {
        registerCanvasElement(Rechteck(100, 100, 300, 300, Colors.GREEN))
        registerController(this)
    }

    override val viewDidLoad: suspend () -> Unit = {

    }

    override fun reactToKeyEvent(event: KeyEvent) {

    }
}

class MyScene2 : SceneTemplate() {
    override val viewWillLoad: suspend () -> Unit = {
        registerCanvasElement(Rechteck(100, 100, 300, 300, Colors.RED))
        registerController(this)
    }

    override fun reactToKeyEvent(event: KeyEvent) {

    }
}