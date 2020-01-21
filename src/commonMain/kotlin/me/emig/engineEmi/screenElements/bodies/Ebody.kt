package me.emig.engineEmi.screenElements.bodies

import com.soywiz.korge.box2d.setPosition
import com.soywiz.korge.box2d.setView
import com.soywiz.korge.view.View
import me.emig.engineEmi.screenElements.ScreenElement
import org.jbox2d.collision.shapes.Shape
import org.jbox2d.dynamics.*

/**
 * Wrapper-Klasse für box2d Body. Ebodies beinhalten einen Body (Das Objekt, auf welchem die physikalischen Simulationen ausgeführt werden. Sie können leichter instanziiert werden und besitzen nebem dem Body sowohl eine Fixture, als auch eine Shape
 * @property x X-Koordinate (Achtung: Box2D-Koordinatensystem!)
 * @property y Y-Koordinate (Achtung: Box2D-Koordinatensystem!)
 * @property density Dichte (Masse im Vergleich zur Umgebung)
 * @property friction Reibungskoeffizient geg. anderen Ebody-Objekten
 * @property restitution Restitution: So etwas wie Weichheit bzw. "Bounciness"
 * @property bodyType Der BodyType. Kann versch. Werte annehmen BodyType.STATIC für unbewegliche (statische Objekte), BodyType.DYNAMIC für beweglice Objekte, welche nach den Gesetzen der Physik interagieren, BodyType.KINEMATIC] für Objekte, die sich unter Nutzerkontroll bewegen dürfen
 */

abstract class Ebody(
    var x: Number,
    var y: Number,
    var density: Float,
    var friction: Float,
    var restitution: Float,
    var bodyType: BodyType
) : ScreenElement {

    /**
     * shape, bd und fixture müssen in den Subklassen überschrieben werden
     * -> s. Box2D body
     */
    abstract val shape: Shape
    val bd = BodyDef()
    var fixture = FixtureDef()
    lateinit var body: Body
    lateinit var view: View
    lateinit var world: World

    /**
     * Initialisiert den Ebody mit der (Box2D)-World der Engine
     * @param world Die Welt
     */
    open suspend fun initForWorld(world: World) {
        this.world = world
        initBody()
    }

    /**
     * Erzeugt die Fixture und fügt Body und Fixture zusammen
     */
    fun setup() {
        createFixture()
        createBodyAttachToFixture()
    }

    /**
     * Bei der Erstellung des Bodies ist die World noch nicht bekannt. Diese wird erst beim Erzeugen des Korge
     * Objekts erstellt. Nach dem die Welt erstellt ist, wird [initBody] aufgerufen, um das EBody-Objekt
     * fertig zu initialiseren
     */
    open suspend fun initBody() {
        body = world.createBody(bd)
        body.createFixture(fixture)
        createView()
        body.setView(view)
    }

    /**
     * Überschreiben!
     * Erzeugt den konkreten View und legt ihn in der Property view an. Siehe Beispiele in [Circle] oder [Rectangle]
     */
    abstract suspend fun createView()

    /**
     * Erzeugt die Fixture.
     */
    open fun createFixture() {
        fixture.run {
            density = this@Ebody.density
            shape = this@Ebody.shape
            friction = this@Ebody.friction
            restitution = this@Ebody.restitution
        }
    }

    /**
     * FÜgt Body und Fixture zusammen
     */
    open fun createBodyAttachToFixture() {
        bd.run {
            type = bodyType
            setPosition(x, y)
        }
    }
}