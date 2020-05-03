package me.emig.engineEmi

import com.soywiz.klock.milliseconds
import com.soywiz.korge.Korge
import com.soywiz.korge.time.delay
import com.soywiz.korge.view.Stage
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.ImageFormat
import com.soywiz.korim.format.ImageFormats
import com.soywiz.korim.format.PNG
import com.soywiz.korim.vector.SizedDrawable
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.ScaleMode
import me.emig.engineEmi.canvasElemente.CanvasElement

/**
 * Die Game-Engine. Sie ist ein Singleton und wird mit [Engine.run] gestartet.
 * @property canvasElements Alle registrieten Objekte vom Typ [CanvasElement]
 * @property bodies Alle registrieten Objekte des Typs [Ebody]
 *
 */
object Engine {
    var config = EngineConfig()
    var stage: Stage? = null
    val loop: suspend Stage.() -> Unit = {
        while (true) {
            this.children.filterIsInstance<CanvasElement>().onEach {
                it.onEveryFrame()
            }
            delay(16.milliseconds)
        }
    }

    suspend operator fun invoke(config: EngineConfig = Engine.config, code: suspend Stage.() -> Unit = {}) = Korge(
        width = config.width.toInt(), height = config.height.toInt(),
        bgcolor = config.bgcolor,
        quality = config.quality,
        targetFps = config.targetFps,
        fullscreen = config.fullscreen,
        scaleAnchor = config.scaleAnchor,
        scaleMode = config.scaleMode,
        icon = config.icon,
        iconPath = config.iconPath,
        iconDrawable = config.iconDrawable,
        clipBorders = config.clipBorders
    ) {
        Engine.stage = this
        Engine.config = config
        code()
        loop()
    }


}


data class EngineConfig(
    val title: String = "EngineEmi",
    val width: Number = 512,
    val height: Number = 512,
    val bgcolor: RGBA = Colors.WHITE,
    val quality: GameWindow.Quality = GameWindow.Quality.PERFORMANCE,
    val icon: Bitmap? = null,
    val iconPath: String? = null,
    val iconDrawable: SizedDrawable? = null,
    val imageFormats: ImageFormat = ImageFormats(PNG),
    val clearEachFrame: Boolean = true,
    val targetFps: Double = 0.0,
    val scaleAnchor: Anchor = Anchor.MIDDLE_CENTER,
    val scaleMode: ScaleMode = ScaleMode.SHOW_ALL,
    val clipBorders: Boolean = true,
    val fullscreen: Boolean = false
)

