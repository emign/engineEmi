package me.emig.engineEmi.screenElements.canvasElements

import com.soywiz.korge.view.Fonts
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.BitmapFont

open class Text(
    x: Number = 0,
    y: Number = 0,
    val string: String = "",
    val groesse: Double = 16.0,
    val farbe: RGBA = Colors.RED,
    val font: BitmapFont = Fonts.defaultFont
) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {
    init {
        updateGraphics()
    }


    final override fun updateGraphics() {
        graphics.apply {
            clear()
            text(text = string, textSize = groesse, color = farbe, font = font)
        }
    }
}
