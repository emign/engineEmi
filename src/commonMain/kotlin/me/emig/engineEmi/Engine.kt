package me.emig.engineEmi

import com.soywiz.korge.Korge
import com.soywiz.korge.internal.KorgeInternal
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.View
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

/**
 * Die Game-Engine. Sie ist ein Singleton und wird mit [Engine.run] gestartet.
 * @property canvasElements Alle registrieten Objekte vom Typ [CanvasElement]
 * @property bodies Alle registrieten Objekte des Typs [Ebody]
 *
 */
object Engine {
    var config = EngineConfig()
    lateinit var stage: Stage

    @KorgeInternal
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
        clipBorders = config.clipBorders,
        debug = config.debug
    ) {
        println("Started")
        Engine.stage = this
        Engine.config = config
        code()
    }

    @Deprecated("Deprecated", ReplaceWith("addchild(view)"))
    fun register(view: View) = stage.addChild(view)

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
    val fullscreen: Boolean = false,
    val debug: Boolean = false
)