package com.tokyo.dungeons.managers

import com.tokyo.dungeons.Dungeons
import org.bukkit.configuration.file.FileConfiguration

object ConfigManager {

    lateinit var config: FileConfiguration

    // TODO might wanna run this! :D
    fun setupConfig(){
        config = Dungeons.instance.config
        Dungeons.instance.saveDefaultConfig()
    }

    fun getDungeonsWorld() = config.getString("Dungeon-World")
}