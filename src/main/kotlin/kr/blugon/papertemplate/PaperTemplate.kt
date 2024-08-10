package kr.blugon.papertemplate

import kr.blugon.papertemplate.command.registerCommand
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: JavaPlugin
class PaperTemplate : JavaPlugin(), Listener {

    override fun onEnable() {
        plugin = this
        logger.info("Plugin enabled")
        Bukkit.getPluginManager().registerEvents(this, this)

        registerCommand()
    }

    override fun onDisable() {
        logger.info("Plugin disabled")
    }
}