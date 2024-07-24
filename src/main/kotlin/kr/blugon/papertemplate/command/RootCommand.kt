package kr.blugon.papertemplate.command

import kr.blugon.kotlinbrigadier.registerCommandHandler
import kr.blugon.kotlinbrigadier.registerEventHandler
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.registerCommand() = RootCommand(this)
class RootCommand(val plugin: JavaPlugin) {

    init {
        val manager = plugin.lifecycleManager
        manager.registerCommandHandler {
            ChildCmd(plugin, this)
        }
    }
}