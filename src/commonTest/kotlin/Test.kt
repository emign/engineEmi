import com.soywiz.klock.milliseconds
import com.soywiz.korau.sound.AudioTone
import com.soywiz.korau.sound.playAndWait
import com.soywiz.korau.sound.toNativeSound
import com.soywiz.korge.internal.KorgeInternal
import com.soywiz.korim.color.RGBA
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.async.suspendTest
import kotlinx.coroutines.*
import me.emig.engineEmi.Engine
import me.emig.engineEmi.graphics.shapes.Rechteck
import kotlin.test.Test

class Test {
    @KorgeInternal
    @Test
    fun mainTest() = run {
        GlobalScope.launchImmediately {
            Engine{
                addChild(Rechteck())
            }
        }
    }
}



