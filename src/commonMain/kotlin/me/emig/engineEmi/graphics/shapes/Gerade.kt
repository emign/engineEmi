package me.emig.engineEmi.graphics.shapes

import com.soywiz.korge.view.Graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint

open class Gerade(x: Number = 0.0,
                  y: Number = 0.0,
                  toX: Number = 0.0,
                  toY: Number = 0.0,
                  fillColor: RGBA = Colors.GREEN,
                  thickness: Number = 10) : Graphics(true) {
    init {
        apply {
            fillStroke(
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
