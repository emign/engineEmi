package me.emig.engineEmi.graphics.animationen

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.measureTime
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Image
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korim.bitmap.BmpSlice
import com.soywiz.korio.async.delay

class Sprite(bitmap : Bitmap) : Image(bitmap) {
    constructor(bmpSlice : BmpSlice) : this(bmpSlice.bmp)
    constructor(initialAnimation : SpriteAnimation) : this(initialAnimation.firstSprite){
        currentAnimation = initialAnimation
        bitmap = currentAnimation?.firstSprite ?: Bitmaps.transparent
    }

    var stop = false
    var currentAnimation : SpriteAnimation? = null
    var currentSpriteIndex = 0

    suspend fun playAnimationForDuration(duration: TimeSpan, spriteAnimation: SpriteAnimation, spriteDisplayTime: TimeSpan = currentAnimation?.frameTime ?: 25.milliseconds) {
        currentAnimation = spriteAnimation
        stop = false
        if (duration > 0.milliseconds) {
            var timeCounter = duration
            while (timeCounter > 0.milliseconds && !stop ) {
                val measuredTime = measureTime {
                    nextSprite()
                    delay(spriteDisplayTime)
                }
                timeCounter -= measuredTime
            }
        }
    }

    suspend fun playAnimation(spriteAnimation: SpriteAnimation) {
        currentAnimation = spriteAnimation
        nextSprite()
       }

    suspend fun playAnimation(times: Int = 1, spriteAnimation: SpriteAnimation, spriteDisplayTime: TimeSpan = currentAnimation?.frameTime ?: 25.milliseconds) {
        currentAnimation = spriteAnimation
        stop = false
        val cycleCount = times*(currentAnimation?.spriteStackSize ?: 0)
        var cycles = 0
        while (cycles < cycleCount && !stop) {
            nextSprite()
            delay(spriteDisplayTime)
            cycles++
        }
    }

    suspend fun playAnimationLooped( spriteAnimation: SpriteAnimation, spriteDisplayTime: TimeSpan = currentAnimation?.frameTime ?: 25.milliseconds) {
        currentAnimation = spriteAnimation
        stop = false
        while (!stop) {
            nextSprite()
            delay(spriteDisplayTime)
        }
    }

    fun stopAnimation() {
        stop = true
    }

    fun nextSprite(){
        bitmap = currentAnimation?.getSprite(++currentSpriteIndex) ?: Bitmaps.transparent
    }

    fun previousSprite(){
        bitmap = currentAnimation?.getSprite(--currentSpriteIndex) ?: Bitmaps.transparent
    }

}