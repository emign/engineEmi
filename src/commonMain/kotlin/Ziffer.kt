import me.emig.engineEmi.screenElements.canvasElements.CanvasElement

class Ziffer : CanvasElement() {
    // Segmente
    val sH = 10 // Segmenthöhe
    val sB = 100 // Segmentbreite

    val a = Segement(sH + 0, 0, sH, sB)
    val b = Segement(sH + sB, sH, sH, sB).apply { rotationDegrees = 180.0 }
    val c = Segement(sH + sB, sH + sB + sH, sH, sB).apply { rotationDegrees = 180.0 }
    val d = Segement(sH + 0, sH + sH + sB, sH, sB)
    val e = Segement(0, sH + sB, sH, sB).apply { rotationDegrees = 180.0 }
    val f = Segement(0, sH, sH, sB).apply { rotationDegrees = 180.0 }
    val g = Segement(sH, sH + sB, sH, sB)

    init {
        addChild(a)
    }

    override fun updateGraphics() {
        // a.updateGraphics()
        println("test")
    }

    override suspend fun onEveryFrame() {
        println(this)
    }
}

