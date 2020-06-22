package me.emig.libEmi.graphics.shapes

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korma.geom.vector.circle

open class Kreis(x: Number = 100.0,
                 y: Number = 100.0,
                 radius: Number = 10.0,
                 fillColor: RGBA = Colors.GREEN,
                 strokeColor: RGBA = Colors.RED,
                 strokeThickness: Number = 0.0) : Shape(x,y){
    init {
        fillStroke(
                ColorPaint(fillColor),
                ColorPaint(strokeColor),
                Context2d.StrokeInfo(thickness = strokeThickness.toDouble())
        ) {
            circle(0.0, 0.0, radius.toDouble())
        }
    }
}

inline fun Container.kreis(
    x: Number = 100.0,
    y: Number = 100.0,
    radius: Number = 10.0,
    fillColor: RGBA = Colors.GREEN,
    strokeColor: RGBA = Colors.RED,
    strokeThickness: Number = 0.0,
    callback : @ViewsDslMarker Kreis.() -> Unit = {}
): Kreis = Kreis(
    x = x,
    y = y,
    radius = radius,
    fillColor = fillColor,
    strokeColor = strokeColor,
    strokeThickness = strokeThickness).addTo(this).apply(callback)

