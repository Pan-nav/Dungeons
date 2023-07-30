package com.tokyo.dungeons

import org.bukkit.Bukkit
import org.bukkit.entity.EntityType

fun String.toEntityTypeOrElse(default: EntityType) = runCatching { EntityType.valueOf(uppercase()) }.getOrElse { default }

fun String.toWorld() = runCatching{Bukkit.getWorld(this)}.getOrNull()