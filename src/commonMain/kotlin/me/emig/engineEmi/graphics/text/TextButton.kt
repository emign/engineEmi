package me.emig.engineEmi.graphics.text

import com.soywiz.korge.html.Html
import com.soywiz.korge.input.onClick
import com.soywiz.korge.ui.*
import com.soywiz.korge.ui.TextButton
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.position
import com.soywiz.korge.view.text


open class TextButton(x: Number = 100.0,
                      y: Number = 100.0,
                      width: Number = 256.0,
                      height: Number = 32.0,
                      text: String = "Button",
                      active: Boolean = true,
                      skin : UISkin = DefaultUISkin,
                      font : Html.FontFace = DefaultUIFont,
                      action: () -> Any = {}) :
        TextButton(
                width.toDouble(),
                height.toDouble(),
                text,
                skin,
                font) {
    init {
        apply {
            position(x, y)
            onClick {
                action()
            }
            if (active)
                enable()
        }
    }
}