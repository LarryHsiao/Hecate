package com.silverhetch.hecate

/**
 * Hecate echo server.
 */
interface HecateServer {

    /**
     * Initial and launch server.
     */
    fun launch(host: String, port: Int)

    /**
     * Shutdown server instance and release resources.
     */
    fun shutdown()
}