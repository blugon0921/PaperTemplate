package kr.blugon.papertemplate.command

import kr.blugon.kotlinbrigadier.registerCommandHandler
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.registerCommand() {
    lifecycleManager.registerCommandHandler {
        register("template") {
            executes {
                sender.sendMessage("Hello, ${sender.name}!")
            }
        }
    }
}