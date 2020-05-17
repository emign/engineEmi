package me.emig.engineEmi.graphics.shapes

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect

open class Rechteck( x: Number = 0.0,
                     y: Number = 0.0,
                     height: Number = 100.0,
                     width: Number = 100.0,
                     fillColor: RGBA = Colors.GREEN,
                     strokeColor: RGBA = Colors.RED,
                     strokeThickness : Number = 0.0) : Shape(x,y) {
    init {
        apply {
            fillStroke(
                    ColorPaint(fillColor),
                    ColorPaint(strokeColor),
                    Context2d.StrokeInfo(thickness = strokeThickness.toDouble())
            ) {
                rect(0,0, width.toDouble(), height.toDouble())
            }
        }
    }
}

suspend inline fun Container.rechteck(
    x: Number = 0.0,
    y: Number = 0.0,
    height: Number = 100.0,
    width: Number = 100.0,
    fillColor: RGBA = Colors.GREEN,
    strokeColor: RGBA = Colors.RED,
    strokeThickness : Number = 0.0,
    callback : @ViewsDslMarker Rechteck.() -> Unit = {}
): Rechteck = Rechteck(
    x = x,
    y = y,
    height = height,
    width = width,
    fillColor = fillColor,
    strokeColor = strokeColor,
    strokeThickness = strokeThickness).addTo(this).apply(callback)

