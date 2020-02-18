package me.emig.engineEmi.module

import DefaultScene
import com.soywiz.korev.KeyEvent
import me.emig.engineEmi.Controller
import me.emig.engineEmi.engine

class SceneController(val scene1: DefaultScene, val scene2: DefaultScene) : Controller {

    override fun reactToKeyEvent(event: KeyEvent) {
        println("switched from 2 to 1")
        engine.switchSceneTo(engine.scene)


    }
}