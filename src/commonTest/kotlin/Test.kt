import com.soywiz.korge.tests.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.*
import me.emig.engineEmi.graphics.shapes.*
import kotlin.test.*

class ShapesTest : ViewsForTesting() {
    @Test
    fun rechteckPosition() = viewsTest {
        val rechteck = Rechteck(33,44,111,222, Colors.GREEN)
        val rect = solidRect(222,111,Colors.RED){
            xy(33,44)
        }
        assertEquals(rechteck.x, rect.x )
        assertEquals(rechteck.y, rect.y )
        assertEquals(rechteck.height, rect.height )
        assertEquals(rechteck.width, rect.width )
        assertEquals(true, rechteck.isVisibleToUser())
    }

    @Test
    fun kreisPosition() = viewsTest {
        val kreis = Kreis(33,44,111, Colors.GREEN)
        val circle = circle(111.0, Colors.GREEN).xy(33,44)
        assertEquals(kreis.x, circle.x )
        assertEquals(kreis.y, circle.y )
        assertEquals(kreis.height, circle.height )
        assertEquals(kreis.width, circle.width )

    }
}