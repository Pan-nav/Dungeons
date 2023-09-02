package com.tokyo.dungeons.type.runnables

import com.tokyo.dungeons.type.managers.DungeonManager
import com.tokyo.dungeons.toWorld
import com.tokyo.dungeons.type.managers.Config
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

object Spawner : BukkitRunnable() {

    val spawnPosList = DungeonManager.getDungeon().spawnPositions
    val world : World = Config.dungeonWorld!!.toWorld()!!


    override fun run() {
        val spawner = spawnPosList.random()
        world.spawnEntity(spawner.getLocation(world), spawner.type)
    }
}