package me.emig.engineEmi

import com.soywiz.korev.KeyEvent
import com.soywiz.korev.MouseEvent
import me.emig.engineEmi.input.KeyEventReacteable
import me.emig.engineEmi.input.MouseEventReacteable

/**
 * Controller sind Objekte, die nicht als [ScreenElement] auf dem View dargestellt werden, aber trotzdem
 * z.B. Events entgegen nehmen können oder Anweisungen an andere Objekte schicken können.
 * Natürlich können auch [ScreenElement]e Controller sein
 */
interface Controller : MouseEventReacteable, KeyEventReacteable {
    override suspend fun reactToMouseEvent(event: MouseEvent) {

    }

    override suspend fun reactToKeyEvent(event: KeyEvent) {

    }
}