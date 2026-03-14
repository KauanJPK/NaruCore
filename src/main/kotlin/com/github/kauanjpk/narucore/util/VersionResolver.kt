package com.github.kauanjpk.narucore.util

import org.bukkit.Bukkit

object VersionResolver {

    val serverPackage: String =
        Bukkit.getServer()::class.java.`package`.name

    val nmsVersion: String =
        serverPackage.substringAfterLast(".")

    val minecraftVersion: Int =
        Bukkit.getBukkitVersion()
            .split(".")[1]
            .split("-")[0]
            .toInt()

    val isLegacy: Boolean
        get() = minecraftVersion <= 8

    val isModern: Boolean
        get() = minecraftVersion >= 17

}