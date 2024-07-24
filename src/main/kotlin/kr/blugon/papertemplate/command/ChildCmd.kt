package kr.blugon.papertemplate.command

import kr.blugon.kotlinbrigadier.BrigadierCommand
import kr.blugon.kotlinbrigadier.player
import kr.blugon.pluginutils.command.ChildCommand
import kr.blugon.pluginutils.command.CommandData
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class ChildCmd (val plugin: JavaPlugin, val registrar: BrigadierCommand) {
    init {
        registrar.register("child") {
            executes {
                player.sendMessage("Child command")
            }
        }
    }
}