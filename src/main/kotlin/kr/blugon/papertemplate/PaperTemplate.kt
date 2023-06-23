package kr.blugon.papertemplate

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class PaperTemplate : JavaPlugin(), Listener {
    override fun onEnable() {
        logger.info("Plugin enabled")
    }

    override fun onDisable() {
        logger.info("Plugin disabled")
    }
}