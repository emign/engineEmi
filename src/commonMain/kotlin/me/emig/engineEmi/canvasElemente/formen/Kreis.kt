package me.emig.engineEmi.canvasElemente.formen

import com.soywiz.korge.view.Circle
import com.soywiz.korge.view.Graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
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
    fillColor: RGBA = Colors.GREEN,
    strokeColor: RGBA = Colors.RED,
    strokeThickness: Number = 0.0
) : Circle(radius = radius.toDouble(), color = fillColor, autoScaling = true) {
    init {
        stroke(strokeColor, Context2d.StrokeInfo(thickness = strokeThickness.toDouble())) {

        }
    }

    companion object {
        suspend operator fun invoke(
            radius: Number = 10.0,
            x: Number = 100.0,
            y: Number = 100.0,
            fillColor: RGBA = Colors.GREEN,
            strokeColor: RGBA = Colors.RED,
            strokeThickness: Number = 0.0
        ) = Graphics(true).apply {

            fillStroke(
                ColorPaint(fillColor),
                ColorPaint(strokeColor),
                Context2d.StrokeInfo(thickness = strokeThickness.toDouble())
            ) {
                circle(x, y, radius)
            }
        }
    }
}

