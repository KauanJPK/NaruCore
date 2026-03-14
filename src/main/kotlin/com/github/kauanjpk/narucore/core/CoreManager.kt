package com.github.kauanjpk.narucore.core

import com.github.kauanjpk.narucore.api.NMSAdapter
import com.github.kauanjpk.narucore.nms.NMSLoader
import com.github.kauanjpk.narucore.packet.PacketManager
import com.github.kauanjpk.narucore.util.VersionResolver
import org.bukkit.plugin.java.JavaPlugin

/**
 * Main core initializer.
 *
 * Responsible for loading internal systems such as:
 * - NMS adapter
 * - Packet API
 * - Version detection
 *
 * This class works as the kernel of the NaruCore framework.
 */
class CoreManager(private val plugin: JavaPlugin) {

    lateinit var nms: NMSAdapter
        private set

    /**
     * Initializes all internal systems.
     */
    fun initialize() {

        plugin.logger.info("Initializing NaruCore systems...")

        // Detect server version
        plugin.logger.info("Detected version: ${VersionResolver.minecraftVersion}")

        // Load NMS adapter
        nms = NMSLoader.load()

        plugin.logger.info("NMS adapter loaded")

        // Initialize packet system
        PacketManager.initialize()

        plugin.logger.info("Packet API initialized")

    }
}