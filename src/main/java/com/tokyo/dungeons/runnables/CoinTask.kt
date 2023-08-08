package com.tokyo.dungeons.runnables

import com.tokyo.dungeons.Dungeons
import org.bukkit.scheduler.BukkitRunnable

object CoinTask : BukkitRunnable(){
    private val coinCache = Dungeons.coinCache

    override fun run() {

        //update coins for users in the list in the database

    }
}