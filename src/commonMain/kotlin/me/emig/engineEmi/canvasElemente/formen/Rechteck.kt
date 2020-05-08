package me.emig.engineEmi.canvasElemente.formen

import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.position
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.vector.rect

/**
 * Zeichnet ein Rechteck
 * @property x X-Koordinate der linken oberen Ecke des Rechtecks
 * @property y Y-Koordinate der linken oberen Ecke des Rechtecks
 * @property hoehe Höhe des Rechtecks
 * @property breite Breite des Rechtecks
 * @property fuellFarbe Füllfarbe. Colors-Objekt
 * @property randFarbe Randfarbe. Colors-Objekt
 * @constructor
 */
open class Rechteck private constructor() {
    companion object {
        suspend operator fun invoke(
            x: Number = 0.0,
            y: Number = 0.0,
            height: Double = 100.0,
            width: Double = 100.0,
            fillColor: RGBA = Colors.GREEN,
            randFarbe: RGBA = Colors.RED
        ) =
            Graphics(true).apply {
                clear()
                fill(fillColor) {
                    rect(-x.toDouble(), -y.toDouble(), width, height)
                }
                position(x, y)
            }
    }
}

