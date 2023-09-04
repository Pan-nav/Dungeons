package com.tokyo.dungeons.serialisation

import com.tokyo.dungeons.serialisation.SpawnerLocation.Companion.toSpawnerLocation
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.EntityType

data class Dungeon(
//    val name: String,
//    val worldname: String,
    val spawnPositions: ArrayList<SpawnerLocation>
) {
//    fun getWorld() = Bukkit.getWorld(worldname)
    /**
     * World parameter is temporary until multi-dungeon support is added.
     */
    fun getSpawnerLocations(world: World) = spawnPositions.map { Location(world, it.x.toDouble(), it.y.toDouble(), it.z.toDouble()) }

    fun getClosestSpawnerPos(location: Location, max: Int) = spawnPositions
        .minBy { it.getDistanceSquared(location) }
        .takeIf { it.getDistanceSquared(location) <= (max * max) }

    fun addSpawner(location: Location, type: EntityType, ability: String, probability: Int) = spawnPositions.add(location.toSpawnerLocation(type))
}