import com.soywiz.korim.color.Colors
import me.emig.engineEmi.engine
import me.emig.engineEmi.screenElements.canvasElements.Rechteck


suspend fun main() {


    engine.run {

        /**
         * Code um die Engine zu konfigurieren.
         */
        init {
            var scene1 = GameScene1()
            scene1.register(Rechteck(100, 100, 100, 100, Colors.GREEN))


            var scene2 = GameScene2()
            scene2.register(Rechteck(100, 100, 200, 200, Colors.RED))
            //  engine.registerGameScenes(scene1, scene2)


            // scene2.registerController(SceneController(scene1, scene2 ))


        }

        /**
         * Code der VOR dem Aufbau des Views ausgeführt wird
         */
        viewWillLoad {


        }

        /**
         * Code, der NACH dem Aufbau des Views ausgeführt wird
         */
        viewDidLoad {

        }

        start()
    }
}

