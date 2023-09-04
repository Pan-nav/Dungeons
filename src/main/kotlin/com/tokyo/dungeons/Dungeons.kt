package com.tokyo.dungeons

import com.tokyo.dungeons.commands.DungeonsCmd
import com.tokyo.dungeons.database.MySQL
import com.tokyo.dungeons.listener.EntityKill
import com.tokyo.dungeons.listener.PlayerListener
import com.tokyo.dungeons.managers.Config
import com.tokyo.dungeons.managers.DungeonManager
import com.tokyo.dungeons.runnables.CoinTask
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
        CoinTask.init(this)

        getCommand("dungeons")?.setExecutor(DungeonsCmd())

        EntityKill().register()
        PlayerListener().register()

        key = NamespacedKey(this, "ShardNote")
    }

    override fun onDisable() {
        database.disconnect()
    }

    private fun database(){
        database.connect()
    }

    private fun Listener.register() = Bukkit.getPluginManager().registerEvents(this, this@Dungeons)


}