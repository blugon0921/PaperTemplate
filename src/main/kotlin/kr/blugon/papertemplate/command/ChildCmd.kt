package kr.blugon.papertemplate.command

import kr.blugon.pluginplus.command.ChildCommand
import kr.blugon.pluginplus.command.CommandData
import org.bukkit.entity.Player

class ChildCmd (private val command: CommandData): ChildCommand {
    override val commandName = "example"
    override var args = arrayListOf("")

    init {
        if(args.size == 1) args = arrayListOf("example", "template")
    }

    override fun onCommand(): Boolean {
        if(command.sender !is Player) return false
        val player = command.sender
        return true
    }
}