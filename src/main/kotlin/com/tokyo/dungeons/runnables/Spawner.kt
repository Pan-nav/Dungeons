package com.tokyo.dungeons.runnables

import com.tokyo.dungeons.managers.Config
import com.tokyo.dungeons.managers.DungeonManager
import com.tokyo.dungeons.util.CustomMob
import com.tokyo.dungeons.util.toWorld
import kotlin.random.Random
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

object Spawner : BukkitRunnable() {

  private val spawnPosList = DungeonManager.getDungeon().spawnPositions
  private val world: World = Config.dungeonWorld!!.toWorld()!!

  override fun run() {
    val spawner = spawnPosList.random()

    val random = Random.nextInt(1, 100)
    if (random <= spawner.probability) {
      CustomMob().spawnCustomMob(spawner)
      return
    } else {
      world.spawnEntity(spawner.getLocation(world), spawner.type)
    }
  }
}
