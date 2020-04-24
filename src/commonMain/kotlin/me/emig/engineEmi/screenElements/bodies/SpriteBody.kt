package me.emig.engineEmi.screenElements.bodies

import com.soywiz.korge.box2d.BoxShape
import com.soywiz.korge.box2d.setView
import com.soywiz.korge.box2d.view
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import me.emig.engineEmi.screenElements.canvasElements.SpriteView
import org.jbox2d.dynamics.BodyType

open class SpriteBody(
    x: Number = 0,
    y: Number = 0,
    bodyType: BodyType = BodyType.STATIC,
    var width: Number = 1,
    var height: Number = 1,
    angle: Float = 0f,
    density: Float = 1f,
    friction: Float = 0.2f,
    restitution: Float = 0.0f,
    var strokeColor: RGBA = Colors.BLUE,
    var strokeThickness: Double = 0.0,
    var scale: Float = 1f / 100f,
    var spriteView: SpriteView

) : Ebody(
    x = x.toDouble(),
    y = y.toDouble(),
    density = density,
    friction = friction,
    restitution = restitution,
    bodyType = bodyType
) {
    lateinit var image: Bitmap
    override var shape = BoxShape(width = width, height = height)

    init {
        setup()
        bd.angle = angle
    }

    override suspend fun initBody() {
        image = spriteView.image.bitmap.bmp
        width = image.width * scale / 10
        height = image.height * scale / 10
        shape = BoxShape(width = width, height = height)
        body = world.createBody(bd)
        fixture.density = density
        fixture.shape = shape
        fixture.friction = friction
        fixture.restitution = restitution
        body.createFixture(fixture)
        createView()
        body.setView(spriteView)
    }

    override suspend fun createView() {
        /*   view = Graphics(autoScaling = true).image(image) {
               position(x, y)
           }.scale(scale).anchor(.5, .5)*/
        // view = Image(image, .5, .5).scale(scale / 10)
        //view = Image(image, .5, .5).scale(scale / 10)
        // println(scale)

        //view = Container().apply { image(image).position(x, y).scale(scale).anchor(.5, .5) }
    }

    fun writeInfo() {
        println("Body: ${this.body.position.x}, ${this.body.position.y}")
        println("View: ${this.body.view!!.x}, ${this.body.view!!.y}")
    }
}



