package com.tokyo.dungeons.runnables

import com.tokyo.dungeons.managers.ConfigManager
import com.tokyo.dungeons.managers.DungeonManager
import com.tokyo.dungeons.toWorld
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

object Spawner : BukkitRunnable() {

    val spawnPosList = DungeonManager.getDungeon().spawnPositions
    val world : World = ConfigManager.getDungeonsWorld()!!.toWorld()!!


    override fun run() {
        val spawner = spawnPosList.random()
        world.spawnEntity(spawner.getLocation(world), spawner.type)
    }
}