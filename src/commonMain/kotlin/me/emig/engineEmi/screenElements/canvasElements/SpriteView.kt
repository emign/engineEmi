package me.emig.engineEmi.screenElements.canvasElements
import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice

/**
 * Ein SpriteView wird vor allem für Sprite-Animationen gebraucht
 * Bei der Erstellung einer [SpriteAnimation] wird immer ein [SpriteView]
 * benötigt, auf welchen die Animationen abgebildet werden.
 * @constructor
 */
class SpriteView : CanvasElement() {
    var sprite: BmpSlice = Bitmaps.transparent
        set(value) {
            field = value
            updateGraphics()
        }
    var image = image(sprite)
        set(value) {
            field = value
            position(x, y)
            scale(scale)
            updateGraphics()
        }

    init {
        updateGraphics()
    }

    /**
     * Aktualisiert das angezeigte Sprite
     * @param sprite BmpSlice
     * @param skalierung Double
     */
    fun refreshViewWithSprite(sprite: BmpSlice, skalierung: Double) {
        this.sprite = sprite
        this.scale = skalierung
    }

    override suspend fun prepareElement() {
        super.prepareElement()
    }


    override fun updateGraphics() {
        image.bitmap = sprite
        image
    }
}