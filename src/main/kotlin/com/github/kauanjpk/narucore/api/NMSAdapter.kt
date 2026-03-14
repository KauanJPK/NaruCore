package com.github.kauanjpk.narucore.api

import org.bukkit.entity.Player

interface NMSAdapter {

    fun sendActionBar(player: Player, message: String)

    fun sendTitle(
        player: Player,
        title: String,
        subtitle: String,
        fadeIn: Int,
        stay: Int,
        fadeOut: Int
    )

    fun getPing(player: Player): Int

}