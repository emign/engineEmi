package me.emig.engineEmi.canvasElemente.textelemente


/*
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
        Engine.stage.launchImmediately {
            textButton(breite.toDouble(), hoehe.toDouble()) {
                text = this@TextButton.text
                position(x, y)
                onClick { aktion() }
                enable(aktiv)
            }
        }
    }
}

 */
