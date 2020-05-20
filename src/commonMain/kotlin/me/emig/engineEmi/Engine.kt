package me.emig.engineEmi

import com.soywiz.klock.TimeProvider
import com.soywiz.klock.seconds
import com.soywiz.korge.Korge
import com.soywiz.korge.internal.KorgeInternal
import com.soywiz.korge.scene.EmptyScene
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.SceneContainer
import com.soywiz.korge.view.*
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.ImageFormat
import com.soywiz.korim.format.ImageFormats
import com.soywiz.korim.format.PNG
import com.soywiz.korim.format.RegisteredImageFormats
import com.soywiz.korim.vector.SizedDrawable
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korio.async.*
import com.soywiz.korio.net.*
import com.soywiz.korma.geom.Anchor
import com.soywiz.korma.geom.ScaleMode


object Engine {
    var config = EngineConfig()
    lateinit var stage: Stage

    @KorgeInternal
    suspend operator fun invoke(config: EngineConfig = Engine.config, code: suspend Stage.() -> Unit = {}) =
             Korge(
                 title = config.title,
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
                 debug = config.debug,
                 args = config.args
            ) {
                //Engine.stage = this // Cannot change this on macos
                //Engine.config = Engine.config // Cannot change this on macos
                code()
             }

    suspend operator fun invoke(module : Module) = Korge(Korge.Config(module))


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
    val debug: Boolean = false,
    val args: Array<String> = arrayOf(),
    val gameWindow: GameWindow? = null,
    val timeProvider: TimeProvider = TimeProvider,
    val injector: AsyncInjector = AsyncInjector()
)

    //suspend fun Stage.alert(message : String) =  this.views.alert(message)
    //suspend fun Stage.confirm(message : String) : Boolean =  this.views.confirm(message)
    //suspend fun Stage.openFileDialog(filter: String? = null, write: Boolean = false, multi: Boolean = false) = views.gameWindow.openFileDialog(filter, write, multi)
    suspend fun Stage.browse(url: URL) = views.gameWindow.browse(url)
    suspend fun Stage.close() = views.gameWindow.close()

fun Container.addChildren(vararg views : View){
    views.forEach {view ->
        this.addChild(view)
    }
}