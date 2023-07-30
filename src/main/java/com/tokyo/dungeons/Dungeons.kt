package com.tokyo.dungeons

import com.tokyo.dungeons.commands.DungeonsCmd
import com.tokyo.dungeons.managers.DungeonManager
import org.bukkit.plugin.java.JavaPlugin

class Dungeons : JavaPlugin() {

    companion object {
        lateinit var instance: Dungeons
    }

    override fun onEnable() {
        instance = this

        DungeonManager

        getCommand("dungeons")?.setExecutor(DungeonsCmd())
    }

}