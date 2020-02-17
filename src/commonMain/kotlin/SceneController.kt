import com.soywiz.korev.KeyEvent
import com.soywiz.korev.MouseEvent
import me.emig.engineEmi.Controller

class SceneController(var scene1: GameScene, var scene2: GameScene2) : Controller {

    var currentScene = scene1

    override suspend fun reactToMouseEvent(event: MouseEvent) {

    }

    override suspend fun reactToKeyEvent(event: KeyEvent) {
        println("EVENT")
        if (currentScene == scene1) {
            scene1.sceneContainer.changeTo<GameScene2>(GameScene2::class)
        }
        if (currentScene == scene1) {
            scene1.sceneContainer.changeTo<GameScene1>(GameScene1::class)
        }
    }
}