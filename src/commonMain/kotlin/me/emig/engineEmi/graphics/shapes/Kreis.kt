package me.emig.engineEmi.graphics.shapes

import com.soywiz.korge.view.Circle
import com.soywiz.korge.view.Graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korma.geom.vector.circle

open class Kreis private constructor(){
    companion object {
        suspend operator fun invoke(
            x: Number = 100.0,
            y: Number = 100.0,
            radius: Number = 10.0,
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

