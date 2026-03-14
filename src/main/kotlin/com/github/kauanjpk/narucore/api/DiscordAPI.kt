package com.github.kauanjpk.narucore.api

import NaruCore
import java.net.HttpURLConnection
import java.net.URL

class DiscordAPI(private val plugin: NaruCore) {

    private val botUrl = "http://cn-01.hostzera.com.br:2768/minecraft/chat"

    fun sendChat(player: String, message: String) {

        try {

            val url = URL(botUrl)
            val conn = url.openConnection() as HttpURLConnection

            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.setRequestProperty("Content-Type", "application/json")

            val json = """{"author":"$player","message":"$message"}"""

            conn.outputStream.use {
                it.write(json.toByteArray())
            }
            println("Body enviado $json")
            conn.inputStream.close()

        } catch (e: Exception) {
            plugin.logger.warning("Erro enviando mensagem ao Discord: ${e.message}")
        }
    }
}