package com.silverhetch.hecate

import com.silverhetch.clotho.connection.socket.TextBaseConn
import com.silverhetch.clotho.connection.socket.TextBaseConnImpl
import org.junit.Assert
import org.junit.Test
import java.net.InetSocketAddress
import javax.net.ServerSocketFactory
import javax.net.SocketFactory

class TextBaseEchoServerTest {

    /**
     * Fire up server and check if echo work.
     */
    @Test
    fun checkInputOutput() {
        val input = "This is input"
        var output = ""
        val server = TextBaseEchoServer(ServerSocketFactory.getDefault())
        server.launch("localhost", 9901)

        SocketFactory.getDefault().createSocket().use {socket->
            socket.connect(InetSocketAddress("localhost", 9901))
            val conn: TextBaseConn = TextBaseConnImpl(
                socket.getInputStream().bufferedReader(),
                socket.getOutputStream().bufferedWriter()
            ) { _, incoming ->
                output = incoming
            }
            conn.launch()
            conn.send(input)
            Thread.sleep(1000)
        }
        server.shutdown()

        Assert.assertEquals(
            input,
            output
        )
    }
}