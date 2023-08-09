package com.tokyo.dungeons.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.EntityType
import org.bukkit.util.StringUtil
import java.util.ArrayList

class DungeonsComplete : TabCompleter {

    companion object {
        /** Cache for performance and readability */
        val entityNames = EntityType.values().map { it.toString() }
    }

    override fun onTabComplete(sender: CommandSender, command: Command,label: String,args: Array<out String>?): List<String> {
        if (args?.size == 1){
            return StringUtil.copyPartialMatches(args[0], listOf("addSource", "removeSource", "startDungeon", "stopDungeon"), ArrayList())
        } else if (args?.size == 2){
            return StringUtil.copyPartialMatches(args[1], entityNames, ArrayList())
        }
        return ArrayList()
    }
}