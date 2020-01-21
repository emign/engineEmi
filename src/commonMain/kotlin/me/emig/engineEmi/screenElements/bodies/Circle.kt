package me.emig.engineEmi.screenElements.bodies

import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.Graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korma.geom.vector.circle
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.dynamics.BodyType

/**
 *
 * @property radius Radius
 * @property fillColor Füllfarbe. Colors-Objekt
 * @property strokeColor Randfarbe. Colors-Objekt
 * @property strokeThickness Randdicke
 * @constructor
 */
open class Circle(
    x: Number = 0,
    y: Number = 0,
    var radius: Number = 0,
    bodyType: BodyType = BodyType.STATIC,
    var fillColor: RGBA,
    angle: Float = 0f,
    density: Float = 1f,
    friction: Float = 0.2f,
    restitution: Float = 0.0f,
    var strokeColor: RGBA = Colors.BLUE,
    var strokeThickness: Double = 0.0
) : Ebody(
    x = x.toDouble(),
    y = y.toDouble(),
    density = density,
    friction = friction,
    restitution = restitution,
    bodyType = bodyType
) {


    override val shape = CircleShape().apply { m_radius = radius.toFloat() }

    init {
        setup()
        bd.angle = angle
    }

    /**
     * Erzeugt den eigentlichen View
     */

    override suspend fun createView() {
        view = Graphics(autoScaling = true).apply {
            if (strokeThickness > 0) {
                fillStroke(
                    Context2d.Color(fillColor),
                    Context2d.Color(strokeColor),
                    Context2d.StrokeInfo(thickness = strokeThickness)
                ) {
                    circle(x, y, radius)
                }
            } else {
                fill(fillColor) {
                    circle(x, y, radius)
                }
            }
        }
    }

    fun writeInfo() {
        println("Body: ${this.body.position.x}, ${this.body.position.y}")
        println("View: ${this.body.view!!.x}, ${this.body.view!!.y}")
    }
}



