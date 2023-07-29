package com.tokyo.dungeons.serialisation

import com.tokyo.dungeons.pow
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.EntityType
import kotlin.math.pow

data class SpawnerLocation(
    val x: Int,
    val y: Int,
    val z: Int,
    val type: EntityType
) {

    companion object {
        fun Location.toSpawnerLocation(type: EntityType) = SpawnerLocation(blockX, blockY, blockZ, type)
    }

    fun getLocation(world: World) = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
    fun getDistanceSquared(to: Location): Double = (to.x-x).pow(2) + (to.y-y).pow(2) + (to.z-z).pow(2)
    fun getDistanceSquared(to: SpawnerLocation): Int = (to.x-x).pow(2) + (to.y-y).pow(2) + (to.z-z).pow(2)
}