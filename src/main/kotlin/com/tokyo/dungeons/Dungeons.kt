package com.tokyo.dungeons

import com.tokyo.dungeons.commands.DungeonsCmd
import com.tokyo.dungeons.type.data.MySQL
import com.tokyo.dungeons.listener.EntityDeathEvent
import com.tokyo.dungeons.type.managers.Config
import com.tokyo.dungeons.type.managers.DungeonManager
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
        Config.init(this)
        database()

        getCommand("dungeons")?.setExecutor(DungeonsCmd())

        register(
            this,
            EntityDeathEvent(),
        )

        key = NamespacedKey(this, "ShardNote")
    }

    override fun onDisable() {
        database.disconnect()
    }

    private fun database() {
        run { database.connect() }
        database.loadCoins()
    }

    private fun register(plugin: JavaPlugin, vararg listeners: Listener) = listeners
        .forEach { listener -> Bukkit.getPluginManager().registerEvents(listener, plugin) }

    private fun Listener.register() = Bukkit.getPluginManager().registerEvents(this, this@Dungeons)


}