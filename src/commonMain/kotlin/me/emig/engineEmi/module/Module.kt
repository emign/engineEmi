package me.emig.engineEmi.module

import DefaultScene
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Camera
import com.soywiz.korgw.GameWindow
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import me.emig.engineEmi.engine
import kotlin.reflect.KClass

class DefaultModule(
    override val quality: GameWindow.Quality,
    override val size: SizeInt,
    override val title: String,
    var camera: Camera,
    var viewWillLoadBody: suspend () -> Unit = {},
    var viewDidLoadBody: suspend () -> Unit = {}
) : Module() {

    override val mainScene: KClass<out Scene> = DefaultScene::class

    override suspend fun AsyncInjector.configure() {
        mapInstance(EngineModuleDependency(title))
        // mapPrototype { engine.scene }
        engine.scenes.forEach {
            mapPrototype { it }
        }
    }
}

class EngineModuleDependency(val value: String)
