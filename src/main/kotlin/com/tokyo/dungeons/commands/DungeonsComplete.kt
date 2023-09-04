package com.tokyo.dungeons.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.EntityType
import org.bukkit.util.StringUtil
import kotlin.collections.ArrayList

class DungeonsComplete : TabCompleter {

    companion object {
        /** Cache for performance and readability */
        val entityNames = EntityType.values().map { it.toString() }
    }

    override fun onTabComplete(sender: CommandSender, command: Command,label: String,args: Array<out String>?): List<String> {
        val result = when (args?.size) {
            1 -> StringUtil.copyPartialMatches(args[0], listOf("addSource", "removeSource", "startDungeon", "stopDungeon"), ArrayList())
            2 -> StringUtil.copyPartialMatches(args[1], entityNames, ArrayList())
            else -> ArrayList()
        }
        return result
    }
}