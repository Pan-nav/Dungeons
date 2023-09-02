package com.tokyo.dungeons.type.serialization

import org.bukkit.configuration.file.FileConfiguration

data class MySQL(val host: String?, val port: Int?, val username: String?, val database: String?, val pass: String?)