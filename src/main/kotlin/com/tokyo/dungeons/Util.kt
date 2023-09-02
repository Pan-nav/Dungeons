package com.tokyo.dungeons

import com.google.gson.GsonBuilder
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import java.util.UUID

fun String.toEntityTypeOrElse(default: EntityType) = runCatching { EntityType.valueOf(uppercase()) }.getOrElse { default }
fun String.toUniqueId(): UUID = UUID.fromString(this)

infix fun Int.pow(index: Int) = (1..index).fold(1) { acc, _ -> acc * this }

fun String.toWorld() = runCatching{ Bukkit.getWorld(this) }.getOrNull()