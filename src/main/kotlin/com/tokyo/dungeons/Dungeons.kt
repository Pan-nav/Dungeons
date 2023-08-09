package com.tokyo.dungeons

import com.tokyo.dungeons.commands.DungeonsCmd
import com.tokyo.dungeons.database.MySQL
import com.tokyo.dungeons.events.EntityKill
import com.tokyo.dungeons.managers.ConfigManager
import com.tokyo.dungeons.managers.DungeonManager
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

class Dungeons : JavaPlugin() {

    companion object {
        lateinit var instance: Dungeons
        lateinit var database: MySQL
        lateinit var key: NamespacedKey
        val coinCache: MutableMap<UUID, Int> = HashMap()
    }

    override fun onEnable() {
        instance = this

        DungeonManager
        ConfigManager.setupConfig()
        database()

        getCommand("dungeons")?.setExecutor(DungeonsCmd())

        EntityKill().register()

        key = NamespacedKey(this, "ShardNote")
    }

    override fun onDisable() {
        database.disconnect()
    }

    private fun database(){
        run { database.connect() }
        database.loadCoins()
    }

    private fun Listener.register() = Bukkit.getPluginManager().registerEvents(this, this@Dungeons)


}