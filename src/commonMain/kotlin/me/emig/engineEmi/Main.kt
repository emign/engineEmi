import com.soywiz.korge.view.Camera
import com.soywiz.korim.color.Colors
import me.emig.engineEmi.engine
import me.emig.engineEmi.module.EngineModuleDependency
import me.emig.engineEmi.module.SceneController
import me.emig.engineEmi.screenElements.canvasElements.Kreis


suspend fun main() {


    engine.run {

        /**
         * Code um die Engine zu konfigurieren.
         */
        init {


            val scene = DefaultScene(
                camera = Camera(),
                viewWillLoadBody = viewWillLoadBody,
                viewDidLoadBody = viewDidLoadBody,
                myDependency = EngineModuleDependency(title)
            )

            scene.canvasElements.add(Kreis(50, 50, 50, Colors.RED))


            val scene2 = DefaultScene(
                camera = Camera(),
                viewWillLoadBody = viewWillLoadBody,
                viewDidLoadBody = viewDidLoadBody,
                myDependency = EngineModuleDependency(title)
            )

            scene2.canvasElements.add(Kreis(50, 50, 50, Colors.BLUE))


            val sceneController = SceneController(scene, scene2)

            engine.registerController(sceneController, scene)
            engine.registerController(sceneController, scene2)
            engine.scenes.add(scene)
            engine.scenes.add(scene2)


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
