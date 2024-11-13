package com.hsung.impactserver

import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketIOServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ImpactServerApplication {
    @Bean
    fun socketIOServer(): SocketIOServer {
        val config = Configuration()
//        config.hostname = Inet4Address.getLocalHost().hostAddress
        config.port = 11092
        val server = SocketIOServer(config)
        server.start()
        println("\n\t\tSocket Server ON\n")
        return server
    }
}

fun main(args: Array<String>) {
    runApplication<ImpactServerApplication>(*args)
}
