package me.emig.engineEmi.canvasElemente.bilder

import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import me.emig.engineEmi.canvasElemente.CanvasElement

/**
 * Lässt ein Bild anzeigen.
 * @property bildDatei Dateiname des Bildes (ggf. mit Pfadangabe). Wurzel ist das "resoures" Verzeichnis
 * @property skalierung Skaliert das Bild um den angegebenen Faktor
 * @constructor
 */
open class Bild(
    x: Number = 100.0,
    y: Number = 100.0,
    var bildDatei: String = "",
    var skalierung: Float = 1.0f,
    val preInitializedBitmap: Bitmap? = null
) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {

    init {
        updateGraphics()
    }


    override suspend fun prepareElement() {
        super.prepareElement()
        val image = preInitializedBitmap ?: resourcesVfs[bildDatei].readBitmap()
        image(image) {
            position(x, y)
        }.scale(skalierung)

    }
}

