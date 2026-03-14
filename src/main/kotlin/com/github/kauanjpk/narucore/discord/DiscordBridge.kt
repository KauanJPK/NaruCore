package com.github.kauanjpk.narucore.discord

import com.sun.net.httpserver.HttpServer
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.net.InetSocketAddress

class DiscordBridge(private val plugin: JavaPlugin) {

    private lateinit var server: HttpServer

    fun start(port: Int = 13241) {

        server = HttpServer.create(InetSocketAddress(port), 0)

        server.createContext("/discord/chat") { exchange ->

            if (exchange.requestMethod == "POST") {

                val body = exchange.requestBody.bufferedReader().readText()

                val parts = body.split("|")
                val author = parts[0]
                val message = parts[1]

                Bukkit.getScheduler().runTask(plugin) {
                    Bukkit.broadcastMessage("§5[Discord] §f$author: $message")
                }

                val response = "ok"
                exchange.sendResponseHeaders(200, response.length.toLong())
                exchange.responseBody.write(response.toByteArray())
                exchange.responseBody.close()
            }
        }

        server.start()
    }

    fun stop() {
        server.stop(0)
    }
}