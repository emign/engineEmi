import com.soywiz.korge.html.Html
import com.soywiz.korge.render.mipmaps
import com.soywiz.korge.ui.DEFAULT_UI_SKIN_IMG
import com.soywiz.korge.ui.DefaultUISkin
import com.soywiz.korge.ui.UISkin
import com.soywiz.korim.bitmap.sliceWithSize
import com.soywiz.korim.color.ColorTransform
import com.soywiz.korim.color.transform
import com.soywiz.korim.font.BitmapFont
import com.soywiz.korim.font.readBitmapFont
import com.soywiz.korim.format.ImageFormat
import com.soywiz.korim.format.RegisteredImageFormats
import com.soywiz.korim.format.readNativeImage
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korio.util.AsyncOnce

/* UI STUFF */
object UiDefaults {

    suspend fun VfsFile.readBitmapFontWithMipmaps(
        imageFormat: ImageFormat = RegisteredImageFormats,
        mipmaps: Boolean = true
    ): BitmapFont =
        readBitmapFont(imageFormat).also { it.atlas.mipmaps(mipmaps) }

    private val otherColorTransform = ColorTransform(0.7, 0.9, 1.0)
    private val OTHER_UI_SKIN_IMG by lazy {
        DEFAULT_UI_SKIN_IMG.withColorTransform(otherColorTransform)
    }

    private val OtherUISkinOnce = AsyncOnce<UISkin>()

    suspend fun defaultUiSkin() = OtherUISkin()
    suspend fun defaultUiFont() = Html.FontFace.Bitmap(resourcesVfs["font/uifont.fnt"].readBitmapFontWithMipmaps())


    suspend fun OtherUISkin(): UISkin = OtherUISkinOnce {
        //val ui = resourcesVfs["korge-ui.png"].readNativeImage().toBMP32().withColorTransform(otherColorTransform)
        val ui = resourcesVfs["font/korge-ui.png"].readNativeImage()

        DefaultUISkin.copy(
            normal = ui.sliceWithSize(0, 0, 64, 64),
            hover = ui.sliceWithSize(64, 0, 64, 64),
            down = ui.sliceWithSize(127, 0, 64, 64),
            backColor = DefaultUISkin.backColor.transform(otherColorTransform)
            //,
            //font = Html.FontFace.Bitmap(getDebugBmpFontOnce())
            //font = Html.FontFace.Bitmap(resourcesVfs["uifont.fnt"].readBitmapFontWithMipmaps())
        )
    }
}







