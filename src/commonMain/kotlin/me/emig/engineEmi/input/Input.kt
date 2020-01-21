package me.emig.engineEmi.input


import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korev.MouseEvent
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


object Keyboard {
    //val keys = Key.values().associate { it to false }.toMutableMap()

    var keys = Key.values().associate { it to false }.toMutableMap()
    var lastKeyDown = Key.UNKNOWN
        private set
    val mutex = Mutex()

    suspend fun keyDown(key: Key) {
        mutex.withLock {
            lastKeyDown = key
            keys[key] = true
        }
    }

    suspend fun keyReleased(key: Key) {
        mutex.withLock {
            if (keys.contains(key)) {
                keys[key] = false
            }

        }
    }

    fun isKeyDown(key: Key): Boolean {
       return keys.getOrElse(key) { return false }
    }

}

interface MouseEventReacteable {
    fun reactToMouseEvent(event: MouseEvent)
}

interface KeyEventReacteable {
    fun reactToKeyEvent(event: KeyEvent)
}