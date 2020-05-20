package me.emig.engineEmi.graphics.shapes

import com.soywiz.korge.view.*

abstract class Shape(x : Number, y : Number) : Graphics(true) {
	init {
		apply {
			xy(x,y)
		}
	}

	override fun toString(): String = "x=$x, y=$y, width=$width, height=$height"


}
