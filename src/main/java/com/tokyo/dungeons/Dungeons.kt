package com.tokyo.dungeons

import com.tokyo.dungeons.commands.DungeonsCmd
import org.bukkit.plugin.java.JavaPlugin

class Dungeons : JavaPlugin() {

    override fun onEnable() {
        ConfigManager.setupConfig(this)
        getCommand("dungeons")?.setExecutor(DungeonsCmd())
    }

}