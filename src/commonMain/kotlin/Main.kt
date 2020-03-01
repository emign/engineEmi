import me.emig.engineEmi.engine

suspend fun main() {

    engine.run {
        init {
            //view.width = 510
            //view.height = 250

        }


        viewWillLoad {
            val stelle1 = Ziffer()
            register(stelle1)
        }


        viewDidLoad {

        }

        start()
    }
}

