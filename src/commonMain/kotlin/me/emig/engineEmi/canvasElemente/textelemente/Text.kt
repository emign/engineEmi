package me.emig.engineEmi.canvasElemente.textelemente

import com.soywiz.korge.view.Fonts
import com.soywiz.korge.view.setText
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.BitmapFont
import me.emig.engineEmi.canvasElemente.CanvasElement

open class Text(
    x: Number = 0,
    y: Number = 0,
    text: String = "Text",
    var groesse: Number = 16.0,
    var farbe: RGBA = Colors.RED,
    var font: BitmapFont = Fonts.defaultFont,
    var filtering: Boolean = false
) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {

    var text = text
        set(value) {
            field = value; setText(value)
        }

    init {
        updateGraphics()
    }


    final override fun updateGraphics() {
        graphics.apply {
            clear()
            text(text = text, textSize = groesse.toDouble(), color = farbe, font = font).apply {
                filtering = this@Text.filtering
            }
        }
        }

}
