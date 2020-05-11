package me.emig.engineEmi.graphics.animationen

/**
 * Ein SpriteView wird vor allem für Sprite-Animationen gebraucht
 * Bei der Erstellung einer [SpriteAnimation] wird immer ein [SpriteView]
 * benötigt, auf welchen die Animationen abgebildet werden.
 * @constructor
 */

/*
open class SpriteView  {
    var sprite: BmpSlice = Bitmaps.transparent
        set(value) {
            image.bitmap = sprite
            field = value
        }
    var image = image(sprite)
        set(value) {
            field = value
            position(x, y)
            scale(scale)
            // updateGraphics()
        }

    init {
        //  updateGraphics()

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

    override suspend fun onEveryFrame() {
        image.bitmap = sprite
        image
    }
}

 */