package me.emig.engineEmi.audio

import com.soywiz.klock.TimeSpan
import com.soywiz.korau.sound.*
import com.soywiz.korio.file.std.resourcesVfs

/**
 * Erzeugt Töne mit entsprechender Frequenz für die entsprechende Dauer
 * @property frequency Double
 * @property duration TimeSpan
 * @constructor
 */
class Tone(val frequency: Double, val duration: TimeSpan) {
    private lateinit var nativeSound: NativeSound
    private lateinit var channel: NativeSoundChannel

    suspend fun play() {
        nativeSound = AudioTone.generate(duration, frequency).toNativeSound()
        channel = nativeSound.play()
    }

    fun stop() {
        if (::channel.isInitialized) {
            channel.stop()
        }
    }
}

/**
 * Spielt einen Sound aus der Datei [filePath] ab.
 * Unterstützt werden WAV und MP3 Dateien
 * @constructor
 */
class Sound(filePath: String) : Audio(filePath = filePath)

/**
 * Spielt Musik aus der Datei [filePath] ab.
 * Unterstützt werden WAV und MP3 Dateien
 * @constructor
 */
class Music(filePath: String) : Audio(filePath = filePath, streaming = true)

abstract class Audio(val filePath: String, val streaming: Boolean = false) {
    private lateinit var sound: NativeSound
    private lateinit var channel: NativeSoundChannel
    suspend fun play() {
        sound = resourcesVfs[filePath].readNativeSound()
        channel = sound.play()
        channel.await()

    }

    fun stop() {
        if (::channel.isInitialized)
            channel.stop()
    }

}
