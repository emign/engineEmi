package me.emig.engineEmi.graphics.shapes

import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.position
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect

open class Rechteck private constructor() {
    companion object {
        operator fun invoke(
            x: Number = 0.0,
            y: Number = 0.0,
            height: Number = 100.0,
            width: Number = 100.0,
            fillColor: RGBA = Colors.GREEN,
            strokeColor: RGBA = Colors.RED,
            strokeThickness : Number = 0.0
        ) =
            Graphics(true).apply {
                fillStroke(
                    ColorPaint(fillColor),
                    ColorPaint(strokeColor),
                    Context2d.StrokeInfo(thickness = strokeThickness.toDouble())
                ) {
                    rect(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
                }

            }
    }
}

