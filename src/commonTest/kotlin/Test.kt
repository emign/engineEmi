import com.soywiz.klock.milliseconds
import com.soywiz.korau.sound.AudioTone
import com.soywiz.korau.sound.playAndWait
import com.soywiz.korau.sound.toNativeSound
import com.soywiz.korim.color.RGBA
import com.soywiz.korio.async.suspendTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.emig.engineEmi.graphics.shapes.Rechteck
import me.emig.engineEmi.engine
import kotlin.test.Test

class Test {
    @Test
    fun lektion18_test() = suspendTest {
        engine.run {

            /**
             * Code um die Engine zu konfigurieren.
             */
            /**
             * Code um die Engine zu konfigurieren.
             */
            init {
                view.width = 510
                view.height = 250
                ArrayController.arrayErzeugen(100)
                ArrayController.wartezeit = 120
                ArrayController.sortieralgorithmus = SelectionSort
                ArrayController.sortieren()
            }

            /**
             * Code der VOR dem Aufbau des Views ausgeführt wird
             */

            /**
             * Code der VOR dem Aufbau des Views ausgeführt wird
             */
            viewWillLoad {

            }

            /**
             * Code, der NACH dem Aufbau des Views ausgeführt wird
             */

            /**
             * Code, der NACH dem Aufbau des Views ausgeführt wird
             */
            viewDidLoad {

            }

            start()
        }
    }
}

object ArrayController {

    lateinit var array: Array<Rechteck>
    var sortieralgorithmus: Sortieralgorithmus = SelectionSort
    var wartezeit = 50L
    private val breite = 5
    private val startX = 10
    private val startY = 200
    private val abstand = 0
    private val randomSubColor: Int
        get() = (0..255).random()

    fun arrayErzeugen(laenge: Int, untereZufallsGrenze: Int = 10, obereZufallsGrenze: Int = 100) {
        array = Array<Rechteck>(laenge) { i ->
            Rechteck(
                x = startX + (i * (breite + abstand)),
                y = startY,
                fuellFarbe = RGBA(randomSubColor, randomSubColor, randomSubColor),
                breite = breite,
                hoehe = (untereZufallsGrenze..obereZufallsGrenze).random()
            ).apply { this.rotationDegrees = 180.0 }
        }
        engine.register(array)
    }

    fun sortieren() {
        CoroutineScope(Dispatchers.Default).launch {
            sortieralgorithmus.sortieren(array)
            positionenAktualisieren()
        }
    }

    suspend fun positionenAktualisieren() {
        array.forEachIndexed { index, rechteck ->
            rechteck.x = startX + (index * (breite + abstand)).toDouble()
        }
        delay(wartezeit)
    }

    suspend fun playSound(rechteck: Rechteck) {
        AudioTone.generate(wartezeit.milliseconds, rechteck.hoehe.toDouble() * 80).toNativeSound().playAndWait()
        // Tone(rechteck.hoehe*80, wartezeit)
    }
}

fun <T> Array<T>.tauscheIndexPositionen(l: Int, r: Int) {
    val tmp = this[l]
    this[l] = this[r]
    this[r] = tmp
}

object SelectionSort : Sortieralgorithmus() {
    override suspend fun sortieren(array: Array<Rechteck>) {
        // Blauer Zeigefinger
        for (i in array.indices) {
            var min = array[i].hoehe.toInt()
            var minPos = i
            var j = i + 1
            // Orangener Zeigefinger
            while (j < array.size) {
                if (array[j].hoehe.toInt() < min) {
                    // Ich habe ein neues Minimum gefunden
                    min = array[j].hoehe.toInt()
                    minPos = j
                }
                j++
            }
            val tmp = array[i]
            array[i] = array[minPos]
            array[minPos] = tmp
            ArrayController.positionenAktualisieren()
        }
    }
}

abstract class Sortieralgorithmus {
    abstract suspend fun sortieren(array: Array<Rechteck>)

    override fun toString(): String {
        return "Sortiere mit: ${this::class}"
    }
}



