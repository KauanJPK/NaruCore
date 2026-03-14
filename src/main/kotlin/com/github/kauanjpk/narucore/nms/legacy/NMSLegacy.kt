package com.github.kauanjpk.narucore.nms.legacy

import com.github.kauanjpk.narucore.api.NMSAdapter
import org.bukkit.entity.Player

class NMSLegacy : NMSAdapter {

    override fun sendActionBar(player: Player, message: String) {

        player.sendMessage(message)

    }

    override fun sendTitle(
        player: Player,
        title: String,
        subtitle: String,
        fadeIn: Int,
        stay: Int,
        fadeOut: Int
    ) {

        player.sendMessage(title)
        player.sendMessage(subtitle)

    }

    override fun getPing(player: Player): Int {

        val handle =
            player.javaClass.getMethod("getHandle").invoke(player)

        val field =
            handle.javaClass.getDeclaredField("ping")

        field.isAccessible = true

        return field.getInt(handle)

    }

}