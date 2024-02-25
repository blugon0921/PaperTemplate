package kr.blugon.papertemplate

import kr.blugon.papertemplate.command.RootCommand
import kr.blugon.pluginutils.command.registerCommands
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class PaperTemplate : JavaPlugin(), Listener {
    companion object {
        lateinit var plugin: JavaPlugin
    }

    override fun onEnable() {
        plugin = this
        logger.info("Plugin enabled")
        Bukkit.getPluginManager().registerEvents(this, this)

        val rootCommand = RootCommand()
        registerCommands(rootCommand, rootCommand, "")
    }

    override fun onDisable() {
        logger.info("Plugin disabled")
    }
}