package me.emig.engineEmi.screenElements.canvasElements

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.measureTime
import com.soywiz.klock.milliseconds
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice
import com.soywiz.korim.bitmap.sliceWithSize
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.delay
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Verwaltet die Sprites für die Animation. Input ist eine SpriteMap (in der Regel PNG oder JPG), welche alle Phasen
 * der Animation enthält. Die einzelnen Sprites werden dann anhand der als Parameter übergebenen
 * Koordinaten ausgelesen.
 * @property x x-Koordinate
 * @property y y-Koordinate
 * @property spriteWidth Breite des Einzelsprites in der Spritemap in Pixeln
 * @property spriteHeight Höhe des Einzelsprites in der Spritemap in Pixeln
 * @property marginTop Abstand des ersten Sprites von der oberen Grenze der Spritemap
 * @property marginLeft Abstand des ersten Sprites von der linken Grenze der Spritemap
 * @property columns Anzahl der Sprites in einer Zeile (von links nach rechts)
 * @property lines Anzahl der Sprites in einer Reihe (von oben nach unten)
 * @property offsetBetweenColumns Abstand zwischen den Spalten in Pixeln
 * @property offsetBetweenLines Abstand zwischen den Zeilen in Pixeln
 * @property skalierung Skalierung
 * @property bildDatei Der String zur Bilddatei in Resources-Ordner
 * @property bitmap Alternativ zur bildDatei kann auch direkt ein Bitmap übergeben werden. Dies ist
 * insbesondere dann sinnvoll, wenn mehrere Animationen die gleiche Spritemap verwenden, um Ressourcen zu schonen
 * @property spriteView Der View des Sprites. Muss vor der Initialisierung manuell erstellt werden
 * @constructor
 */
class SpriteAnimation(
    var x: Number = 100.0,
    var y: Number = 100.0,
    var spriteWidth: Int = 16,
    var spriteHeight: Int = 16,
    var marginTop: Int = 0,
    var marginLeft: Int = 0,
    var columns: Int = 1,
    var lines: Int = 1,
    var offsetBetweenColumns: Int = 0,
    var offsetBetweenLines: Int = 0,
    var skalierung: Double = 1.0,
    var bildDatei: String = "",
    val bitmap: Bitmap? = null,
    var spriteView: SpriteView
) {

    private val defaultSprite = Bitmaps.transparent
    private var sprites: MutableList<BmpSlice> = mutableListOf(defaultSprite)
    private var currentSpriteIndex = 0
    private val currentSprite: BmpSlice
        get() = sprites[currentSpriteIndex]

    private var cycles = 0
    private var stop = false

    init {
        CoroutineScope(Dispatchers.Default).launch {
            prepareElement()
        }
        updateSpriteView()
    }

    private fun updateSpriteView() {
        spriteView.apply {
            x = x
            y = y
            refreshViewWithSprite(currentSprite, skalierung)
        }
    }

    private suspend fun prepareElement() {
        var line = 0
        repeat(columns) { spalte ->
            val resourceBitmap: Bitmap = if (bitmap is Bitmap) {
                bitmap
            } else {
                resourcesVfs[bildDatei].readBitmap()
            }
            addSpriteToList(
                resourceBitmap.sliceWithSize(
                    marginLeft + (spriteWidth + offsetBetweenColumns) * spalte,
                    marginTop + (spriteHeight + offsetBetweenLines) * line,
                    spriteWidth,
                    spriteHeight
                )
            )
            if (spalte % columns == 0 && spalte != 0) {
                line++
            }
        }
        updateSpriteView()
    }

    private fun addSpriteToList(sprite: BmpSlice) {
        sprites.add(sprite)
        if (sprites.first() == defaultSprite) {
            sprites.removeAt(0)
        }
    }

    private fun nextSprite() {
        currentSpriteIndex = (currentSpriteIndex + 1) % sprites.size
        cycles++
        updateSpriteView()
    }

    private fun previousSprite() {
        currentSpriteIndex = (currentSpriteIndex - 1) % sprites.size
        updateSpriteView()
    }


    /**
     * Spielt die Animation für die Dauer von [duration] ab. Jedes Frame der Animation wird dabei [spriteDisplayTime] lang
     * angezeigt. Die Animation wird immer wieder von vorne begonnen und beginnt am aktuellen Animationsschritt
     * @param spriteDisplayTime Dauer der Anzeige jedes einzelnen Frames (Animationsschrittes)
     * @param duration Dauer der gesamten Animation
     */
    fun playForDuration(duration: TimeSpan, spriteDisplayTime: TimeSpan = 25.milliseconds) {
        if (duration > 0.milliseconds) {
            CoroutineScope(Dispatchers.Default).launch {
                var timeCounter = duration
                while (timeCounter > 0.milliseconds) {
                    val measuredTime = measureTime {
                        nextSprite()
                        delay(spriteDisplayTime)
                    }
                    timeCounter -= measuredTime
                }
            }
        }
    }


    /**
     * Spielt die Animation so oft ab, wie in [times] bestimmt
     * @param times Anzahl der Animationen
     * @param spriteDisplayTime So lange wird ein Animationsschritt angezeigt
     */
    fun play(times: Int = 1, spriteDisplayTime: TimeSpan = 25.milliseconds) {
        CoroutineScope(Dispatchers.Default).launch {
            val currentCycles = cycles
            while (cycles < currentCycles + times) {
                nextSprite()
                delay(spriteDisplayTime)
            }
        }
    }

    /**
     * Spielt die Animation für immer ab
     * @param spriteDisplayTime So lange wird ein Animationsschritt angezeigt
     */
    fun playLooped(spriteDisplayTime: TimeSpan = 25.milliseconds) {
        stop = false
        CoroutineScope(Dispatchers.Default).launch {
            while (!stop) {
                nextSprite()
                delay(spriteDisplayTime)
            }
        }
    }

    /**
     * Spielt die Animation ab
     * @param spriteDisplayTime So lange wird ein Animationsschritt angezeigt
     */
    fun play(spriteDisplayTime: TimeSpan = 25.milliseconds) {
        CoroutineScope(Dispatchers.Default).launch {
            nextSprite()
            delay(spriteDisplayTime)
        }
    }

    /**
     * Stoppt Animationen, die mit [playLooped] gestartet wurden
     */
    fun stop() {
        stop = true
    }

}
