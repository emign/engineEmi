import com.soywiz.klock.*
import com.soywiz.korge.tests.*
import com.soywiz.korge.tiled.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korim.format.writeBitmap
import com.soywiz.korio.async.*
import com.soywiz.korio.file.std.*
import com.soywiz.korio.lang.*
import com.soywiz.korma.geom.*
import kotlinx.coroutines.*
import me.emig.engineEmi.addChildren
import me.emig.engineEmi.graphics.shapes.*
import me.emig.engineEmi.graphics.text.*
import me.emig.engineEmi.graphics.text.Text
import kotlin.jvm.*
import kotlin.test.*

class ViewsTest : ViewsForTesting() {
    @Test
    fun testrechteck() = viewsTest {
        val rechteck = Rechteck(33, 44, 111, 222, Colors.GREEN)
        val rect = solidRect(222.0, 111.0, Colors.RED) {
            xy(33, 44)
        }
        assertEquals(rechteck.x, rect.x)
        assertEquals(rechteck.y, rect.y)
        assertEquals(rechteck.height, rect.height)
        assertEquals(rechteck.width, rect.width)
        assertEquals(true, rechteck.isVisibleToUser())
    }

    @Test
    fun testKreis() = viewsTest {
        val kreis = Kreis(33, 44, 111, Colors.GREEN)
        val circle = circle(111.0, Colors.GREEN).xy(33, 44)
        assertEquals(kreis.x, circle.x)
        assertEquals(kreis.y, circle.y)
        assertEquals(kreis.height.toInt(), circle.height.toInt())
        assertEquals(kreis.width.toInt(), circle.width.toInt())
        assertEquals(222, kreis.width.toInt())
        assertEquals(222, kreis.width.toInt())
    }

    @Test
    fun testGeraden() = viewsTest {
        val gerade = Gerade(40, 50, 60, 70)
        addChild(gerade)
        assertEquals(40, gerade.x.toInt())
        assertEquals(50, gerade.y.toInt())
        assertEquals(15, gerade.globalBounds.left.toInt())
        assertEquals(125, gerade.globalBounds.right.toInt())
        assertEquals(25, gerade.globalBounds.top.toInt())
        assertEquals(145, gerade.globalBounds.bottom.toInt())
    }

    @Test
    fun testTextButton() = viewsTest {
        var clicked = false
        val textButton = TextButton(200,200,active = true, action = { clicked = true } )
        textButton.simulateClick()
        assert(clicked)
        assert(textButton.isVisibleToUser())
    }
}
