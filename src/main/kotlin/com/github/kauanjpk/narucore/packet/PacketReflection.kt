package com.github.kauanjpk.narucore.packet

import com.github.kauanjpk.narucore.util.VersionResolver

object PacketReflection {

    fun packetClass(name: String): Class<*> {

        val path =
            if (VersionResolver.isModern)
                "net.minecraft.network.protocol.game.$name"
            else
                "net.minecraft.server.${VersionResolver.nmsVersion}.$name"

        return Class.forName(path)

    }

}