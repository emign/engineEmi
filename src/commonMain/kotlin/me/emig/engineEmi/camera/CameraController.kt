package me.emig.engineEmi.camera

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.seconds
import com.soywiz.korev.KeyEvent
import com.soywiz.korev.MouseEvent
import com.soywiz.korge.tween.moveBy
import com.soywiz.korge.tween.moveTo
import com.soywiz.korge.view.Camera
import com.soywiz.korio.async.launch
import com.soywiz.korma.interpolation.Easing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import me.emig.engineEmi.Controller

abstract class CameraController(val camera: Camera) : Controller {

    override fun reactToKeyEvent(event: KeyEvent) {

    }

    override fun reactToMouseEvent(event: MouseEvent) {

    }

    /**
     * Bewegt die Kamera zu den angegebenen Koordinaten
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param time Die Zeitspanne für die Bewegeung. Zum Beispiel 0.25.seconds
     * @param easing Die Easing-Animation
     */
    fun moveCameraTo(x: Int, y: Int, time: TimeSpan = 0.25.seconds, easing: Easing = Easing.LINEAR) {
        runInScope { camera.moveTo(x, y, time, easing) }
    }

    /**
     * Bewegt die Kamera um die angegeben Delta-x und Delta-y Werte
     * @param dx Int
     * @param dy Int
     * @param time Die Zeitspanne für die Bewegeung. Zum Beispiel 0.25.seconds
     * @param easing Die Easing-Animation
     */
    fun moveCameraBy(dx: Int, dy: Int, time: TimeSpan = 0.25.seconds, easing: Easing = Easing.LINEAR) {
        runInScope { camera.moveBy(dx, dy, time, easing) }
    }

    private fun runInScope(action: suspend () -> Any) {
        CoroutineScope(Dispatchers.Default).launch {
            action()
        }
    }
}