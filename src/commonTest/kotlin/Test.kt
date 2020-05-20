import com.soywiz.korge.tests.*
import com.soywiz.korge.tiled.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.*
import com.soywiz.korio.lang.*
import com.soywiz.korma.geom.*
import me.emig.engineEmi.addChildren
import me.emig.engineEmi.graphics.shapes.*
import kotlin.test.*

class ShapesTest : ViewsForTesting() {
    @Test
    fun testrechteck() = viewsTest {
        val rechteck = Rechteck(33, 44, 111, 222, Colors.GREEN)
        val rect = solidRect(222, 111, Colors.RED) {
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
    fun testSpriteAnimation() = viewsTest {
        val spriteMap = resourcesVfs["gfx/character/character.png"].readBitmap()
        val spriteAnimationLeft = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 16,
            spriteHeight = 32,
            marginTop = 96,
            marginLeft = 1,
            columns = 4,
            rows = 1
        )

        val spriteAnimationRight = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 16,
            spriteHeight = 32,
            marginTop = 32,
            marginLeft = 1,
            columns = 4,
            rows = 1
        )

        val spriteAnimationUp = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 16,
            spriteHeight = 32,
            marginTop = 64,
            marginLeft = 1,
            columns = 4,
            rows = 1
        )

        val spriteAnimationDown = SpriteAnimation(
            spriteMap = spriteMap,
            spriteWidth = 16,
            spriteHeight = 32,
            marginTop = 0,
            marginLeft = 1,
            columns = 4,
            rows = 1
        )

        val player1 = Sprite(spriteAnimationDown).apply {
            scale(3)
            xy(100,200)
        }
        val player2 = Sprite(spriteAnimationDown).apply {
            scale(3)
            xy(100,100)
        }
        addChildren(player1, player2)
        assert(player1.isVisibleToUser())
        assert(player2.isVisibleToUser())
    }

    @Test
    fun testTileMap() = viewsTest {
        val tiledMap = resourcesVfs["map/sample.tmx"].readTiledMap()
        fixedSizeContainer(width, height, clip = true) {
            val camera = camera {

                assert(tiledMapView(tiledMap) {
                }.isVisibleToUser())
            }

        }

    }
}