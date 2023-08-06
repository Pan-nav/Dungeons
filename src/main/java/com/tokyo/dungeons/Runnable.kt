package com.tokyo.dungeons

import com.tokyo.dungeons.managers.ConfigManager
import com.tokyo.dungeons.managers.DungeonManager
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

object Runnable : BukkitRunnable() {

    val spawnPosList = DungeonManager.getDungeon().spawnPositions
    val world : World = ConfigManager.getDungeonsWorld()!!.toWorld()!!


    override fun run() {
        val spawner = spawnPosList.random()
        world.spawnEntity(spawner.getLocation(world), spawner.type)
    }
}