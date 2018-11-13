package com.silverhetch.hecate

import com.silverhetch.clotho.connection.socket.TextBaseConn
import com.silverhetch.clotho.connection.socket.TextBaseConnImpl
import java.net.Socket

/**
 * Socket implementation of [TextBaseConn]
 */
class SocketConn(private val socket: Socket, private val onMessage: (TextBaseConn, String) -> Unit) : TextBaseConn {
    private lateinit var textBaseConn: TextBaseConn

    override fun close() {
        socket.close()
    }

    override fun launch() {
        textBaseConn = TextBaseConnImpl(
            socket.getInputStream().bufferedReader(),
            socket.getOutputStream().bufferedWriter(),
            onMessage
        ).apply { launch() }
    }

    override fun send(msg: String) {
        textBaseConn.send(msg)
    }
}