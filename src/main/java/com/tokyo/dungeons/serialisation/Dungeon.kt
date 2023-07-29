package com.tokyo.dungeons.serialisation

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
        .minBy { it.getLocation(location.world).distanceSquared(location) }
        .takeIf { it.getLocation(location.world).distanceSquared(location) <= (max * max) }

    fun addSpawner(location: Location, type: EntityType) = spawnPositions
        .add(SpawnerLocation(location.blockX, location.blockY, location.blockZ, type))
}