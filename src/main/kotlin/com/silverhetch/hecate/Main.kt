package com.silverhetch.hecate

import com.silverhetch.clotho.log.BeautyLog
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import javax.net.ServerSocketFactory

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val cmd = DefaultParser().parse(
            Options().apply {
                addOption("h", "host", true, "The host name.")
                addOption("p", "port", true, "THe port server use.")
            }, args
        )

        val host = if (cmd.hasOption("h")) cmd.getOptionValue("h") else "localhost"
        val port = if (cmd.hasOption("p")) cmd.getOptionValue("p").toInt() else 8080

        val log = BeautyLog().fetch()
        val server = TextBaseEchoServer(ServerSocketFactory.getDefault())
        server.launch(host, port)
        log.info("Running Hecate Server on $host:$port, press ctl^C to stop")

        Runtime.getRuntime().addShutdownHook(Thread {
            // TODO: for now this is not working when closing application with ctrl^c
            server.shutdown()
        })
    }
}
