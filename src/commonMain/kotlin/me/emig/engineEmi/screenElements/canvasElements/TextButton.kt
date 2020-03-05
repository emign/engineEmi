package me.emig.engineEmi.screenElements.canvasElements

import UiDefaults
import com.soywiz.korge.input.onClick
import com.soywiz.korge.ui.uiTextButton
import com.soywiz.korge.view.position
import com.soywiz.korio.async.launch
import me.emig.engineEmi.engine

/**
 * Zeichnet einen Kreis
 * @property radius Radius
 * @property x X-Koordiante des Mittelpunkts (Standard-Koordinatensystem)
 * @property y Y-Koordiante des Mittelpunkts (Standard-Koordinatensystem)
 * @property fuellFarbe Füllfarbe als Colors-Objekt
 * @property randFarbe Randfarbe als Colors-Objekt
 * @constructor
 */
open class TextButton(
    x: Number = 100.0,
    y: Number = 100.0,
    var breite: Number = 256.0,
    var hoehe: Number = 32.0,
    var text: String = "Button",
    var aktiv: Boolean = true,
    var aktion: () -> Any = {}

) : CanvasElement(x = x.toDouble(), y = y.toDouble()) {
    init {
        updateGraphics()
    }


    override fun updateGraphics() {
        engine.sceneContainer?.stage?.launch {
            uiTextButton(
                width = breite, height = hoehe, text = text
                , skin = UiDefaults.defaultUiSkin(), textFont = UiDefaults.defaultUiFont()
            ) {
                text = text
                position(x, y)
                onClick {
                    aktion()
                }
                enable(aktiv)
            }
        }
    }


}
