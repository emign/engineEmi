import com.soywiz.korim.color.Colors
import me.emig.engineEmi.engine
import me.emig.engineEmi.screenElements.canvasElements.Rechteck


suspend fun main() {
    engine.run {

        /**
         * Code um die Engine zu konfigurieren.
         */
        init {
            register(Rechteck(100, 100, 100, 100, Colors.BLUE))

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
