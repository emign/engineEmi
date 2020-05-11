package me.emig.engineEmi.graphics.shapes

import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.position
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.vector.rect

open class Rechteck private constructor() {
    companion object {
        suspend operator fun invoke(
            x: Number = 0.0,
            y: Number = 0.0,
            height: Number = 100.0,
            width: Number = 100.0,
            fillColor: RGBA = Colors.GREEN,
            randFarbe: RGBA = Colors.RED
        ) =
            Graphics(true).apply {
                clear()
                fill(fillColor) {
                    rect(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
                }
            }
    }
}

