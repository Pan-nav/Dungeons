package com.tokyo.dungeons.runnables

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.database.MySQL
import org.bukkit.scheduler.BukkitRunnable

object CoinTask : BukkitRunnable() {
    private val coinCache = Dungeons.coinCache

    fun init(plugin: Dungeons) {
        runTaskTimerAsynchronously(plugin, 6000, 6000)
    }

    override fun run() {
        //update coins for users in the list in the database
        for (cache in coinCache) {
            MySQL.updateCoins(cache.key)
        }
    }
}