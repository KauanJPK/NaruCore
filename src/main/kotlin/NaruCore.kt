import com.github.kauanjpk.narucore.api.DiscordAPI
import com.github.kauanjpk.narucore.commands.CommandManager
import com.github.kauanjpk.narucore.core.CoreManager
import com.github.kauanjpk.narucore.listeners.ChatListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * Main plugin class.
 *
 * Responsible for starting the NaruCore framework.
 */
class NaruCore : JavaPlugin() {

    lateinit var core: CoreManager
        private set

    lateinit var discordAPI: DiscordAPI
        private set

    override fun onEnable() {

        logger.info("Starting NaruCore...")

        /**
         * Initialize core framework systems.
         */
        core = CoreManager(this)
        core.initialize()

        /**
         * Initialize Discord bridge.
         */
        discordAPI = DiscordAPI(this)

        server.pluginManager.registerEvents(
            ChatListener(discordAPI),
            this
        )

        logger.info("Discord API enabled")

        /**
         * Register plugin commands.
         */
        CommandManager(this).registerCommands()

        logger.info("Commands registered")

        logger.info("NaruCore enabled successfully.")

    }

    override fun onDisable() {

        logger.info("NaruCore shutting down.")

    }
}