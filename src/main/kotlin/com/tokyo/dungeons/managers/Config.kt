package com.tokyo.dungeons.managers

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.MySQL
import org.bukkit.configuration.file.FileConfiguration

object Config {
    private lateinit var config: FileConfiguration

    val mySQL get() = loadMySQLDetails()
    val dungeonWorld get() = config.getString("dungeon-world")

    fun init(dungeons: Dungeons) {
        config = dungeons.config
        Dungeons.instance.saveDefaultConfig()
    }

    private fun loadMySQLDetails(): MySQL {
        return MySQL(
            config.getString("mysql.host"),
            config.getInt("mysql.port"),
            config.getString("mysql.username"),
            config.getString("mysql.database"),
            config.getString("mysql.pass")
        )
    }
}