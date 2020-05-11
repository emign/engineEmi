package me.emig.engineEmi.graphics.shapes

import com.soywiz.korge.view.Graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint

/**
 * @property x X-Koordinate des Ursprungs der Geraden (Standard-Koordinatensystem)
 * @property y Y-Koordinate des Ursprungs der Geraden (Standard-Koordinatensystem)
 * @property toX X-Koordinate des Ziels der Geraden (Standard-Koordinatensystem))
 * @property toY Y-Koordinate des Ziels der Geraden (Standard-Koordinatensystem)
 * @property dicke Dicke der Geraden
 * @property fuellFarbe Füllfarbe als Colors Objekt
 * @property randFarbe Randfarbe als Colors Objekt
 * @constructor
 */
open class Gerade private constructor() {
    companion object {
        suspend operator fun invoke(
            x: Number = 0.0,
            y: Number = 0.0,
            toX: Number = 0.0,
            toY: Number = 0.0,
            fillColor: RGBA = Colors.GREEN,
            thickness: Number = 10
        ) =
            Graphics(true).apply {
                fillStroke(
                    //Context2d.Color(fillColor),
                    //Context2d.Color(fillColor),
                    ColorPaint(fillColor),
                    ColorPaint(fillColor),
                    Context2d.StrokeInfo(thickness = thickness.toDouble())
                ) {
                    moveTo(x.toDouble(), y.toDouble()); lineTo(
                    x.toDouble() + toX.toDouble(),
                    y.toDouble() + toY.toDouble()
                )
                }
            }
    }
}



