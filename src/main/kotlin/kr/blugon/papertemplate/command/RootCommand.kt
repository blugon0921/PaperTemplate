package kr.blugon.papertemplate.command

import kr.blugon.pluginplus.command.CommandData
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class RootCommand: CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val data = CommandData(sender, command, label, args)
        val commands = arrayListOf(ChildCmd(data))
        for(child in commands) {
            if(command.name != child.commandName) continue
            return child.onCommand()
        }
        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        val data = CommandData(sender, command, label, args)
        val commands = arrayListOf(ChildCmd(data))
        for(child in commands) {
            if(command.name != child.commandName) continue
            val original = child.args
            val result = ArrayList<String>()
            for(r in original) if(r.lowercase().startsWith(args[args.size-1].lowercase())) result.add(r)
            return result
        }
        return mutableListOf()
    }
}