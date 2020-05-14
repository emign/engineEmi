package me.emig.engineEmi.audio

import com.soywiz.klock.TimeSpan
import com.soywiz.korau.sound.*
import com.soywiz.korge.view.*
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.*
import me.emig.engineEmi.graphics.animationen.*

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
class Sound(filePath: String, looped: Boolean = false) : Audio(filePath = filePath, looped = looped)

/**
 * Spielt Musik aus der Datei [filePath] ab.
 * Unterstützt werden WAV und MP3 Dateien
 * @constructor
 */
class Music(filePath: String, looped: Boolean = false) : Audio(filePath = filePath, streaming = true, looped = looped)

abstract class Audio(val filePath: String, val streaming: Boolean = false, var looped : Boolean = false) {
    private lateinit var sound: NativeSound
    private lateinit var channel: NativeSoundChannel
    fun play() {
        CoroutineScope(Dispatchers.Default).launch {
            sound = resourcesVfs[filePath].readSound()
            channel = sound.play()
            channel.await()
        }.invokeOnCompletion {
            if (looped)
                play()
        }
    }

    fun stop() {
        if (::channel.isInitialized){
            channel.stop()
            looped = false
        }

    }

}
