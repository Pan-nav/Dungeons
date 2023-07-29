package com.tokyo.dungeons

import com.tokyo.dungeons.commands.DungeonsCmd
import org.bukkit.plugin.java.JavaPlugin

class Dungeons : JavaPlugin() {

    companion object {
        lateinit var instance: Dungeons
    }

    override fun onEnable() {
        instance = this

        ConfigManager

        getCommand("dungeons")?.setExecutor(DungeonsCmd())
    }

}