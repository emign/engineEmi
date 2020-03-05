package me.emig.engineEmi.screenElements.canvasElements


import com.soywiz.korge.view.Container
import com.soywiz.korge.view.sgraphics
import me.emig.engineEmi.Log

import me.emig.engineEmi.screenElements.ScreenElement


abstract class CanvasElement(
    x: Double = 0.0,
    y: Double = 0.0
) : Container(), ScreenElement {

    init {
        super.x = x
        super.y = y
    }

    /**
     * Autoskalierender Vektor->Bitmap Wandler
     */
    val graphics = sgraphics {
    }

    /**
     * Hier werden die Animationsbefehle gespeichert.
     */
    var animationRoutine: () -> Any = {}

    /**
     * Muss in Subklassen überschrieben werden, falls man das Objekt animieren will.
     * Wird im default ca. 60 Mal pro Sekunde aufgerufen.
     * Änderungen der Parameter wie etwa x und y werden so direkt angezeigt, wenn man sie überschreibt.
     * Alternativ kann man auch bestehenden Objekten neue Animationen zuweisen. Die geht etwa mit Hilfe von [animate]
     */
    open suspend fun onEveryFrame() {
        animationRoutine()
    }

    open suspend fun animate() {
        Log.log("Überholt. Bitte onEveryFrame() verwedenn")
        onEveryFrame()
    }

    /**
     * Bereite das Element vor (wird in Subklassen überschrieben).
     * Siehe Implementierung von [Kreis] oder [Rechteck] für Beispiele
     */
    open suspend fun prepareElement() {}

    /**
     * Zeichnet das Objekt. Siehe Implementierung von [Kreis] oder [Rechteck] für Beispiele
     */
    open fun updateGraphics() {}


}
