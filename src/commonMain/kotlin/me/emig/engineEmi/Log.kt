package me.emig.engineEmi

import com.soywiz.klogger.Logger

object Log {
    val logger = Logger("engineEmig")
    fun log(s: String) {
        println(s)
        logger.log(Logger.Level.INFO) { s }
    }
}