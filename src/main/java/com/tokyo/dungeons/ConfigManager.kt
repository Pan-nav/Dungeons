package com.tokyo.dungeons

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.tokyo.dungeons.serialisation.Dungeon
import java.io.File


object ConfigManager {

    val file = File(Dungeons.instance.dataFolder, "Dungeons.json").also {
        it.runCatching {
            if (!exists()) {
                parentFile.mkdirs()
                createNewFile()
            }
        }.onFailure { ex -> ex.printStackTrace() }
    }

    val gson = Gson().newBuilder().setPrettyPrinting().create()
    val type = object: TypeToken<ArrayList<Dungeon>>() {}.type

    val dungeons: ArrayList<Dungeon> = file.reader().use {
        try {
            gson.fromJson(it, type)
        } catch (e: JsonSyntaxException) {
            Dungeons.instance.logger.severe("Failed to load dungeons from file, If this is the first time loading, this is expected, if not, there is an error in your config")
            e.printStackTrace()
            arrayListOf(Dungeon(arrayListOf()))
        }
    }

    fun saveDungeons() = file.writer().use { gson.toJson(dungeons, type, it) }

    /**
     * Temporary method until support for multiple dungeons is added.
     */
    fun getDungeon() = dungeons[0]

}