package com.github.kauanjpk.narucore.listeners

import com.github.kauanjpk.narucore.api.DiscordAPI
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener(private val discordAPI: DiscordAPI) : Listener {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {

        val player = event.player.name
        val message = event.message

        discordAPI.sendChat(player, message)
    }
}