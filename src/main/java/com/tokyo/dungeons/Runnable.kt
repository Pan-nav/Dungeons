package com.tokyo.dungeons

import com.tokyo.dungeons.managers.ConfigManager
import com.tokyo.dungeons.managers.DungeonManager
import com.tokyo.dungeons.serialisation.SpawnerLocation
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable
import kotlin.random.Random

object Runnable : BukkitRunnable() {

    val spawnPosList = DungeonManager.getDungeon().spawnPositions
    val world : World = ConfigManager.getDungeonsWorld()!!.toWorld()!!


    override fun run() {
        val index = Random.nextInt(spawnPosList.size)
        val spawnerLocation : SpawnerLocation = spawnPosList[index]
        val spawnLoc : Location = spawnerLocation.getLocation(world)

        world.spawnEntity(spawnLoc, spawnerLocation.type)
    }
}