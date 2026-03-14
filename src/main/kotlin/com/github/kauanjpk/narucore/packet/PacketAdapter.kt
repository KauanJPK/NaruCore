package com.github.kauanjpk.narucore.packet

import org.bukkit.entity.Player

interface PacketAdapter {

    fun sendPacket(player: Player, packet: Any)

}