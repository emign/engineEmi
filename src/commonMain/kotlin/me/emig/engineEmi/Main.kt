package me.emig.engineEmi

import com.soywiz.korio.async.launch
import me.emig.engineEmi.screenElements.bodies.Ebody
import me.emig.engineEmi.screenElements.canvasElements.CanvasElement
import kotlinx.coroutines.GlobalScope


/**
 * Das Default (und eigentlich immer) das einzige Engine-Objekt
 */
val engine = Engine()

/**
 * Startpunkt für alle Programme.
 * Hier werden u.a. [Ebody] und [CanvasElement] Objekte bei der [Engine] registriert.
 * Es gibt drei Bereiche:
 * init : Dieser Code-Block wird zur Konfiguration der Engine verwendet. Hier kann man z.B. die Höhe und Breite des Fensters festlegen.
 * Wenn man diesen Block leer lässt, werden Standard-Werte geladen
 * viewWillLoad: Dieser Code-Block wird NACH der Konfiguration aber VOR dem Aufbau des Views (der Anzeige) ausgeführt. Hier sollte man
 * seine Objekte bei der Engine registrieren
 * viewDidLoad: Dieser Code-Block wird NACH dem der View komplett aufgebaut wurde ausgeführt. Hier sollte man Code platzieren, der darauf
 * angewiesen ist, dass Objekte bereits fertig erstellt und registriert wurden. Dies trifft vor allem auf [Ebody] Objekte zu.
 */
fun main() {
    GlobalScope.launch {
        engine.run {

            /**
             * Code um die Engine zu konfigurieren.
             */
            init {

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
}

