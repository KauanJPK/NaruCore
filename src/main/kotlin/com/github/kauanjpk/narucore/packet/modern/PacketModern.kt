package com.github.kauanjpk.narucore.packet.modern

import com.github.kauanjpk.narucore.packet.PacketAdapter
import org.bukkit.entity.Player

class PacketModern : PacketAdapter {

    override fun sendPacket(player: Player, packet: Any) {

        val handle =
            player.javaClass.getMethod("getHandle").invoke(player)

        val connection =
            handle.javaClass.getField("connection").get(handle)

        connection.javaClass.methods
            .first { it.name == "send" }
            .invoke(connection, packet)

    }

}