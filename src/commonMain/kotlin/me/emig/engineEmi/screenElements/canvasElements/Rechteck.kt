package me.emig.engineEmi.screenElements.canvasElements

import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.position
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.vector.rect
import me.emig.engineEmi.screenElements.canvasElements.CanvasElement

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
open class Rechteck(
    var hoehe: Number = 0.0,
    var breite: Number = 0.0,
    x: Number = 0.0,
    y: Number = 0.0,
    var fuellFarbe: RGBA = Colors.GREEN,
    var randFarbe: RGBA = Colors.RED
) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {


    var fillColor: RGBA = fuellFarbe
        set(value) {
            field = value; updateGraphics()
        }

    var strokeColor: RGBA = randFarbe
        set(value) {
            field = value; updateGraphics()
        }

    init {
        updateGraphics()
        super.width = breite.toDouble()
        super.height = hoehe.toDouble()
    }

    final override fun updateGraphics() {
        graphics {
            clear()
            fill(fillColor) {
                rect(-x, -y, width, height)
            }
            position(x, y)
        }
    }
}

