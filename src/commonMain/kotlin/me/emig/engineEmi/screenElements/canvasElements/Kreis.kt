package me.emig.engineEmi.screenElements.canvasElements

import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korma.geom.vector.circle

/**
 * Zeichnet einen Kreis
 * @property radius Radius
 * @property x X-Koordiante des Mittelpunkts (Standard-Koordinatensystem)
 * @property y Y-Koordiante des Mittelpunkts (Standard-Koordinatensystem)
 * @property fuellFarbe Füllfarbe als Colors-Objekt
 * @property randFarbe Randfarbe als Colors-Objekt
 * @constructor
 */
open class Kreis(
    radius: Number = 10.0,
    x: Number = 100.0,
    y: Number = 100.0,
    fuellFarbe: RGBA = Colors.GREEN,
    randFarbe: RGBA = Colors.RED,
    randDicke: Number = 0.0

) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {


    var radius: Double = radius.toDouble()
        set(value) {
            field = value; updateGraphics()
        }

    var fuellFarbe: RGBA = fuellFarbe
        set(value) {
            field = value; updateGraphics()
        }

    var randFarbe: RGBA = randFarbe
        set(value) {
            field = value; updateGraphics()
        }

    var randDicke: Number = randDicke
        set(value) {
            field = value; updateGraphics()
        }


    init {
        updateGraphics()
    }


    final override fun updateGraphics() {
        graphics.apply {
            clear()
            fillStroke(
                Context2d.Color(fuellFarbe),
                Context2d.Color(randFarbe),
                Context2d.StrokeInfo(thickness = randDicke.toDouble())
            ) {
                circle(x, y, radius)
            }
        }
    }
}
