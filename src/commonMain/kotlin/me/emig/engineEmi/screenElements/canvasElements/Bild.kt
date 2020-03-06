package me.emig.engineEmi.screenElements.canvasElements

import com.soywiz.korge.view.image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

/**
 * Lässt ein Bild anzeigen.
 * @property bildDatei Dateiname des Bildes (ggf. mit Pfadangabe). Wurzel ist das "resoures" Verzeichnis
 * @property skalierung Skaliert das Bild um den angegebenen Faktor
 * @constructor
 */
open class Bild(
    x: Number = 100.0,
    y: Number = 100.0,
    var bildDatei: String,
    var skalierung: Float = 1.0f
) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {


    init {
        updateGraphics()
    }


    override suspend fun prepareElement() {
        super.prepareElement()
        image(resourcesVfs[bildDatei].readBitmap()) {
            position(x, y)
        }.scale(skalierung)

    }


}
