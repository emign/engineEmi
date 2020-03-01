import com.soywiz.korim.color.Colors
import me.emig.engineEmi.screenElements.canvasElements.Rechteck

class Segement(x: Number, y: Number, hoehe: Number, breite: Number) :
    Rechteck(x = x.toInt(), y = y.toInt(), breite = breite.toInt(), hoehe = hoehe.toInt(), fuellFarbe = Colors.RED) {

    init {
        println("Added $this")
    }


}