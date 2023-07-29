package com.tokyo.dungeons

import org.bukkit.entity.EntityType

fun String.toEntityTypeOrElse(default: EntityType) = runCatching { EntityType.valueOf(uppercase()) }.getOrElse { default }