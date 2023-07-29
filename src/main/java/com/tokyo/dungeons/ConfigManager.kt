package com.tokyo.dungeons

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.EntityType


class ConfigManager {

    companion object{
        lateinit var config : FileConfiguration
        lateinit var spawners : HashMap<Location, EntityType>

        fun setupConfig(plugin : Dungeons){
            config = plugin.config
            plugin.saveDefaultConfig()

            spawners = loadSpawners()
        }

        private fun loadSpawners() : HashMap<Location, EntityType>{

            val spawners : HashMap<Location, EntityType> = hashMapOf()
            val spawnPointSection = config.getConfigurationSection("Spawn-Point")

            if (spawnPointSection != null) {
                for (key in spawnPointSection.getKeys(false)) {
                    val spawnerEntry = spawnPointSection.getConfigurationSection(key)
                    val location = parseSpawnerLocation(spawnerEntry)

                    val entityType = spawnerEntry?.getString("type")?.toEntityTypeOrElse(EntityType.ZOMBIE)

                    spawners[location!!] = entityType!!
                }

            }
            return spawners
        }

        private fun parseSpawnerLocation(entry: ConfigurationSection?): Location? {
            val worldName = entry?.getString("world")
            val x = entry?.getDouble("x")
            val y = entry?.getDouble("y")
            val z = entry?.getDouble("z")

            val world = Bukkit.getWorld(worldName!!)
                ?: // Invalid spawner entry, return null or handle the error as needed
                return null

            return Location(world, x!!, y!!, z!!)
        }
    }


}