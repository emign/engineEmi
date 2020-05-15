package me.emig.engineEmi.graphics.text

import com.soywiz.korge.annotations.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.BitmapFont
import com.soywiz.korim.font.DefaultTtfFont
import com.soywiz.korim.vector.HorizontalAlign
import com.soywiz.korim.vector.VerticalAlign
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korim.vector.paint.LinearGradientPaint
import com.soywiz.korim.vector.paint.Paint


@KorgeExperimental
open class Text(x: Number = 0,
                y: Number = 0,
                text: String = "Text",
                size: Number = 16.0,
                color: RGBA = Colors.RED,
                font: BitmapFont =  BitmapFont(DefaultTtfFont, size, paint = ColorPaint(color)),
                horizontalAlign: HorizontalAlign = HorizontalAlign.LEFT,
                verticalAlign: VerticalAlign = VerticalAlign.BOTTOM) : Graphics(true) {
    init {
        apply {
            text2(text, size.toDouble(), color, font, horizontalAlign, verticalAlign)
                    .apply {
                        position(x, y)
                    }
        }
    }
}

@KorgeExperimental
suspend inline fun Container.text(
    x: Number = 0,
    y: Number = 0,
    text: String = "Text",
    size: Number = 16.0,
    color: RGBA = Colors.RED,
    font: BitmapFont =  BitmapFont(DefaultTtfFont, size, paint = ColorPaint(color)),
    horizontalAlign: HorizontalAlign = HorizontalAlign.LEFT,
    verticalAlign: VerticalAlign = VerticalAlign.BOTTOM,
    callback : @ViewsDslMarker Text.() -> Unit = {}
) : Text = Text(
    x = x,
    y = y,
    text = text,
    size = size,
    color = color,
    font = font,
    horizontalAlign = horizontalAlign,
    verticalAlign = verticalAlign).addTo(this).apply(callback)