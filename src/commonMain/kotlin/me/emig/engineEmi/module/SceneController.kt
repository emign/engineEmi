package me.emig.engineEmi.module

import DefaultScene
import com.soywiz.korev.KeyEvent
import me.emig.engineEmi.Controller
import me.emig.engineEmi.engine

class SceneController(val scene1: DefaultScene, val scene2: DefaultScene) : Controller {

    override fun reactToKeyEvent(event: KeyEvent) {


        engine.switchSceneTo(scene2)
        println("switched from 1 to 2")


        if (engine.activeScene == scene2) {
            engine.switchSceneTo(scene1)
            println("switched from 2 to 1")
        }

    }
}