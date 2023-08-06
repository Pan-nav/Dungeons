package com.tokyo.dungeons.managers

import com.tokyo.dungeons.Dungeons
import org.bukkit.configuration.file.FileConfiguration

object ConfigManager {

    lateinit var config: FileConfiguration

    fun setupConfig(){
        config = Dungeons.instance.config
        Dungeons.instance.saveDefaultConfig()
    }

    fun getDungeonsWorld(): String? {return config.getString("Dungeon-World")}
}