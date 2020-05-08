package me.emig.engineEmi.canvasElemente.bilder

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.position
import com.soywiz.korge.view.scale
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.VectorPath

/**
 * Lässt ein Bild anzeigen.
 * @property bildDatei Dateiname des Bildes (ggf. mit Pfadangabe). Wurzel ist das "resoures" Verzeichnis
 * @property skalierung Skaliert das Bild um den angegebenen Faktor
 * @constructor
 */

open class Bild(
    x: Number,
    y: Number,
    bitmap: Bitmap,
    anchorX: Double = 0.0,
    anchorY: Double = anchorX,
    hitShape: VectorPath? = null,
    scale: Double = 1.0,
    smoothing: Boolean = true
) : Image(
    bitmap = bitmap,
    anchorX = anchorX,
    anchorY = anchorY,
    hitShape = hitShape,
    smoothing = smoothing
) {
    companion object {
        suspend operator fun invoke(
            x: Number,
            y: Number,
            bitmap: String,
            anchorX: Double = 0.0,
            anchorY: Double = anchorX,
            hitShape: VectorPath? = null,
            scale: Double = 1.0,
            smoothing: Boolean = true
        ): Image {
            val image = resourcesVfs[bitmap].readBitmap()
            return Bild(
                x = x,
                y = y,
                bitmap = image,
                anchorX = anchorX,
                anchorY = anchorY,
                hitShape = hitShape,
                smoothing = smoothing
            )
        }
    }


    init {
        position(x, y)
        scale(scale)
    }
}