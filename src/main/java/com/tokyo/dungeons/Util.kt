package com.tokyo.dungeons

import org.bukkit.Bukkit
import org.bukkit.entity.EntityType

fun String.toEntityTypeOrElse(default: EntityType) = runCatching { EntityType.valueOf(uppercase()) }.getOrElse { default }
infix fun Int.pow(index: Int) = (1..index).fold(1) { acc, _ -> acc * this }

fun String.toWorld() = runCatching{ Bukkit.getWorld(this) }.getOrNull()