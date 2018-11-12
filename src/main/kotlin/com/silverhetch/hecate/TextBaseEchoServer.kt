package com.silverhetch.hecate

import com.silverhetch.clotho.connection.socket.TextBaseConn
import java.net.InetSocketAddress
import java.net.ServerSocket
import javax.net.ServerSocketFactory

/**
 * Text echo server.
 */
class TextBaseEchoServer(private val serverSocketFactory: ServerSocketFactory) : HecateServer {
    private lateinit var serverSocket: ServerSocket
    private var running = false

    override fun launch(host: String, port: Int) {
        if (running) {
            return
        }
        running = true
        Thread {
            val clientConn = ArrayList<TextBaseConn>()
            serverSocket = serverSocketFactory.createServerSocket()
            serverSocket.bind(InetSocketAddress(host, port))
            while (running) {
                clientConn.add(
                    SocketConn(serverSocket.accept()) { conn, msg ->
                        conn.send(msg)
                    }.apply { this.launch() }
                )
            }
            clientConn.forEach { it.close() }
            clientConn.clear()
        }.start()
    }

    override fun shutdown() {
        running = false
        serverSocket.close()
    }
}