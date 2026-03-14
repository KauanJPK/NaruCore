package com.github.kauanjpk.narucore.packet.legacy

import com.github.kauanjpk.narucore.packet.PacketAdapter
import org.bukkit.entity.Player

class PacketLegacy : PacketAdapter {

    override fun sendPacket(player: Player, packet: Any) {

        val handle =
            player.javaClass.getMethod("getHandle").invoke(player)

        val connection =
            handle.javaClass.getField("playerConnection").get(handle)

        connection.javaClass.methods
            .first { it.name == "sendPacket" }
            .invoke(connection, packet)

    }

}