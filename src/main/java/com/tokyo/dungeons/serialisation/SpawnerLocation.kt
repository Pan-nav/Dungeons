package com.tokyo.dungeons.serialisation

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.EntityType

data class SpawnerLocation(
    val x: Int,
    val y: Int,
    val z: Int,
    val type: EntityType
) {

    fun getLocation(world: World?) = Location(world, x.toDouble(), y.toDouble(), z.toDouble())

}