package com.github.kauanjpk.narucore.nms.modern

import com.github.kauanjpk.narucore.api.NMSAdapter
import org.bukkit.entity.Player

class NMSModern : NMSAdapter {

    override fun sendActionBar(player: Player, message: String) {

        try {

            val method = player.javaClass.getMethod(
                "sendActionBar",
                String::class.java
            )

            method.invoke(player, message)

        } catch (e: Exception) {

            player.sendMessage(message)

        }

    }

    override fun sendTitle(
        player: Player,
        title: String,
        subtitle: String,
        fadeIn: Int,
        stay: Int,
        fadeOut: Int
    ) {

        try {

            val method = player.javaClass.getMethod(
                "sendTitle",
                String::class.java,
                String::class.java,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )

            method.invoke(player, title, subtitle, fadeIn, stay, fadeOut)

        } catch (e: Exception) {

            player.sendMessage(title)
            player.sendMessage(subtitle)

        }

    }

    override fun getPing(player: Player): Int {

        return try {

            val handle =
                player.javaClass.getMethod("getHandle").invoke(player)

            val pingField =
                handle.javaClass.getDeclaredField("ping")

            pingField.isAccessible = true

            pingField.getInt(handle)

        } catch (e: Exception) {

            -1

        }

    }

}