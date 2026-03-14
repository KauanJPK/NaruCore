package com.github.kauanjpk.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import java.util.UUID

class AdministratorCommands {

    class BanCommand : CommandExecutor {
        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
            if (!sender.hasPermission("narucore.ban")) { sender.sendMessage("§cVocê não tem permissão!"); return true }
            if (args.isEmpty()) { sender.sendMessage("§cUso: /ban <jogador> [motivo]"); return true }

            val target = Bukkit.getPlayer(args[0])
            if (target == null) { sender.sendMessage("§cJogador não encontrado!"); return true }

            val reason = if (args.size > 1) args.sliceArray(1 until args.size).joinToString(" ") else "Sem motivo"

            target.isBanned = true
            target.kickPlayer("§cVocê foi banido!\n§7Motivo: §f$reason")
            Bukkit.broadcastMessage("§c${target.name} foi banido por ${sender.name}!")
            sender.sendMessage("§aJogador banido com sucesso!")
            return true
        }
    }

    class KickCommand : CommandExecutor {
        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
            if (!sender.hasPermission("narucore.kick")) { sender.sendMessage("§cVocê não tem permissão!"); return true }
            if (args.isEmpty()) { sender.sendMessage("§cUso: /kick <jogador> [motivo]"); return true }

            val target = Bukkit.getPlayer(args[0])
            if (target == null) { sender.sendMessage("§cJogador não encontrado!"); return true }

            val reason = if (args.size > 1) args.sliceArray(1 until args.size).joinToString(" ") else "Sem motivo"

            target.kickPlayer("§cVocê foi expulso!\n§7Motivo: §f$reason")
            Bukkit.broadcastMessage("§c${target.name} foi expulso por ${sender.name}!")
            return true
        }
    }

    class PingCommand : CommandExecutor {
        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
            if (sender !is Player) { sender.sendMessage("§cApenas jogadores podem usar este comando!"); return true }

            val ping = try {
                val craftPlayer = sender.javaClass.getMethod("getHandle").invoke(sender)
                val pingField = craftPlayer.javaClass.getDeclaredField("ping")
                pingField.getInt(craftPlayer)
            } catch (e: Exception) { 0 }

            sender.sendMessage("§aSeu ping: §f${ping}ms")
            return true
        }
    }

    class MuteCommand : CommandExecutor, Listener {
        private val mutedPlayers = mutableMapOf<UUID, Long>()

        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
            if (!sender.hasPermission("narucore.mute")) { sender.sendMessage("§cVocê não tem permissão!"); return true }
            if (args.isEmpty()) { sender.sendMessage("§cUso: /mute <jogador> [motivo]"); return true }

            val target = Bukkit.getPlayer(args[0])
            if (target == null) { sender.sendMessage("§cJogador não encontrado!"); return true }

            val reason = if (args.size > 1) args.sliceArray(1 until args.size).joinToString(" ") else "Sem motivo"
            mutedPlayers[target.uniqueId] = System.currentTimeMillis() + 300_000

            target.sendMessage("§cVocê foi mutado!\n§7Motivo: §f$reason")
            sender.sendMessage("§aJogador mutado com sucesso!")
            return true
        }

        @EventHandler
        fun onPlayerChat(event: PlayerChatEvent) {
            val player = event.player
            val muteEnd = mutedPlayers[player.uniqueId]
            if (muteEnd != null && System.currentTimeMillis() < muteEnd) {
                event.isCancelled = true
                player.sendMessage("§cVocê está mutado e não pode falar!")
            } else {
                mutedPlayers.remove(player.uniqueId)
            }
        }
    }
}