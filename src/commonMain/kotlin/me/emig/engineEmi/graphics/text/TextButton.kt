package me.emig.engineEmi.graphics.text

import com.soywiz.korge.html.Html
import com.soywiz.korge.input.onClick
import com.soywiz.korge.ui.*
import com.soywiz.korge.ui.TextButton
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.font.*
import com.soywiz.korim.vector.*
import com.soywiz.korim.vector.paint.*


open class TextButton(x: Number = 100.0,
                      y: Number = 100.0,
                      width: Number = 256.0,
                      height: Number = 32.0,
                      text: String = "Button",
                      active: Boolean = true,
                      skin : UISkin = DefaultUISkin,
                      font : Html.FontFace = DefaultUIFont,
                      action: suspend () -> Any = {}) :
        TextButton(
                width.toDouble(),
                height.toDouble(),
                text,
                skin,
                font) {
    init {
        apply {
            position(x.toDouble(), y.toDouble())
            onClick {
                action()
            }
            if (active)
                enable()
        }
    }
}

// TODO: make inline when inline allows suspend
fun Container.textbutton(
    x: Number = 100.0,
    y: Number = 100.0,
    width: Number = 256.0,
    height: Number = 32.0,
    text: String = "Button",
    active: Boolean = true,
    skin : UISkin = DefaultUISkin,
    font : Html.FontFace = DefaultUIFont,
    action:  suspend () -> Any = {},
    callback : @ViewsDslMarker TextButton.() -> Unit = {}
) : TextButton = TextButton(
    x = x,
    y = y,
    width = width,
    height = height,
    text = text,
    active = active,
    skin = skin,
    font = font,
    action = action).addTo(this).apply(callback)