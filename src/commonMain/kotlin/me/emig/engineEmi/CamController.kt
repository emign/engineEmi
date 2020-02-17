package me.emig.engineEmi

import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.view.Camera
import me.emig.engineEmi.camera.CameraController

class CamController(camera: Camera) : CameraController(camera) {

    override suspend fun reactToKeyEvent(event: KeyEvent) {
        when (event.key) {
            Key.RIGHT -> moveCameraBy(-16, 0, 0.25.seconds)
            Key.LEFT -> moveCameraBy(+16, 0, 0.25.seconds)
            Key.DOWN -> moveCameraBy(0, -16, 0.25.seconds)
            Key.UP -> moveCameraBy(0, +16, 0.25.seconds)
            else -> {
            }
        }
    }

}