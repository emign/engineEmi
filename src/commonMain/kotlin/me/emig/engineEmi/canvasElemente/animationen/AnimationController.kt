package me.emig.engineEmi.canvasElemente.animationen

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import me.emig.engineEmi.Controller
import me.emig.engineEmi.Log


/**
 * Verwaltete mehrere Animationen. Etwa die Links-, Rechts-, Oben-, Unten- Laufanimationen eines Charakters
 * @property animations Beinhaltete die Animationen
 */
class AnimationController : Controller {
    private var animations = mutableMapOf<String, SpriteAnimation>()

    /**
     * Fügt eine Animation unter einem bestimmten Namen hinzu
     * @param name Name der Animation
     * @param animation Die Animation
     */
    fun addAnimation(name: String = "main", animation: SpriteAnimation) {
        animations.put(name, animation)
    }

    /**
     * Spielt eine vorher mit [AnimationController.addAnimation] hinzugefügte ab
     * @param name Name der Animation
     * @param timeBetweenSprites Wartezeit zwischen den Animationsschritten
     */
    fun play(
        name: String,
        spriteDisplayTime: TimeSpan = 25.milliseconds,
        duration: TimeSpan? = null,
        looped: Boolean? = null,
        times: Int? = null
    ) {
        if (animations[name] == null) {
            Log.log("Animation $name nicht gefunden")
        } else {
            when {
                looped != null -> animations[name]?.playLooped(spriteDisplayTime)
                times != null -> animations[name]?.play(times, spriteDisplayTime)
                duration != null -> animations[name]?.playForDuration(duration, spriteDisplayTime)
                else -> animations[name]?.play(spriteDisplayTime)
            }
        }
    }

    /**
     * Hält die benannte Animation an
     * @param name String
     */
    fun stop(name: String) {
        animations[name]?.stop()
    }

}