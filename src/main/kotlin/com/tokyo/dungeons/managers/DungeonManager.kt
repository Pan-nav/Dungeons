package com.tokyo.dungeons.managers

import com.google.gson.JsonSyntaxException
import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.util.GSON
import com.tokyo.dungeons.serialisation.Dungeon
import java.io.File


object DungeonManager {

    val file = File(Dungeons.instance.dataFolder, "Dungeons.json").also {
        it.runCatching {
            if (!exists()) {
                parentFile.mkdirs()
                createNewFile()
            }
        }.onFailure { ex -> ex.printStackTrace() }
    }

    val dungeons: ArrayList<Dungeon> = file.reader().use {
        try {
            GSON.fromJson(it, ArrayList<Dungeon>()::class.java)
        } catch (e: JsonSyntaxException) {
            Dungeons.instance.logger.severe("Failed to load dungeons from file, If this is the first time loading, this is expected, if not, there is an error in your config")
            e.printStackTrace()
            arrayListOf(Dungeon(arrayListOf()))
        }
    }

    fun saveDungeons() = file.writer().use { GSON.toJson(dungeons, it) }

    /**
     * Temporary method until support for multiple dungeons is added.
     */
    fun getDungeon() = dungeons[0]

}