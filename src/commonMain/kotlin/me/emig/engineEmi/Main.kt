import me.emig.engineEmi.engine
import me.emig.engineEmi.screenElements.canvasElements.Kreis


suspend fun main() {


    engine.run {

        /**
         * Code um die Engine zu konfigurieren.
         */
        init {


            register(Kreis(50, 50, 50))


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
