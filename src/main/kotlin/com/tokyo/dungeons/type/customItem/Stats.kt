package com.tokyo.dungeons.type.customItem

data class Stats(
    val strength: Double = 1.0,
    val critChance: Double = 1.0,
    val critDamage: Double = 1.0, // critical damage multiplier
    val attackSpeed: Double = 1.0,
    val mana: Double = 1.0,
    val luck: Double = 1.0,
    val defense: Double = 1.0,
    val health: Double = 1.0,
    val speed: Double = 1.0,
)
