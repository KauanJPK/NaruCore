package com.github.kauanjpk.narucore.commands

import NaruCore
import com.github.kauanjpk.commands.AdministratorCommands

import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener

class CommandManager(private val plugin: NaruCore) {

    fun registerCommands() {

        register("ban", AdministratorCommands.BanCommand())
        register("kick", AdministratorCommands.KickCommand())
        register("ping", AdministratorCommands.PingCommand())

        val mute = AdministratorCommands.MuteCommand()
        register("mute", mute)
        registerListener(mute)

        plugin.logger.info("Todos os comandos registrados.")
    }

    private fun register(name: String, executor: CommandExecutor) {

        val cmd = plugin.getCommand(name)

        if (cmd == null) {
            plugin.logger.warning("Comando /$name não encontrado.")
            return
        }

        cmd.executor = executor
    }

    private fun registerListener(listener: Listener) {
        plugin.server.pluginManager.registerEvents(listener, plugin)
    }
}