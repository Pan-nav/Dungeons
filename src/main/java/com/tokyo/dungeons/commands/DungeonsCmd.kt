package com.tokyo.dungeons.commands

import com.tokyo.dungeons.ConfigManager
import com.tokyo.dungeons.Dungeons
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class DungeonsCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender !is Player){
            println("This command can only be used by a player.")
            return true
        }

        val player : Player = sender

        if (args == null || args.isEmpty()) {
            player.sendMessage("Please follow the format: /dungeons <addSource|removeSource> [entityType]")
            return true
        }

        if (args[0] == "removeSource"){
            val closestLocation = findClosestLocation(player.location, ConfigManager.spawners.keys)
            if (closestLocation != null){
                ConfigManager.spawners.remove(closestLocation)
                saveUpdatedSpawnersToConfig()
                player.sendMessage("Closest Location removed.")
            } else {
                player.sendMessage("No nearby location found.")
            }

            return true
        } else if (args[0] == "addSource"){
            if (args.size < 2) {
                player.sendMessage("Please specify the entity type (e.g., /dungeons addSource Zombie)")
                return true
            }

            val entityType: EntityType = args[1].toEntityTypeOrElse(EntityType.ZOMBIE)

            val roundedX = player.location.blockX.toDouble()
            val roundedY = player.location.blockY.toDouble()
            val roundedZ = player.location.blockZ.toDouble()

            // Create a new Location with rounded coordinates and player's world
            val location = Location(player.world, roundedX, roundedY, roundedZ)

            // Save the new entity type to the config and HashMap
            ConfigManager.spawners[location] = entityType
            saveUpdatedSpawnersToConfig()

            player.sendMessage("Source added at your location with the entity type: ${entityType.name}")
        }

        return true
    }


    private fun saveUpdatedSpawnersToConfig() {
        // TODO Using GSON Might be easier here? - Penguin

        val config = ConfigManager.config

        // Clear the existing 'Spawn-Point' section in the config
        config.set("Spawn-Point", null)

        // Re-populate the 'Spawn-Point' section with the updated spawners data
        val index = 1
        for ((location, entityType) in ConfigManager.spawners) {
            val locationPath = "Spawn-Point.$index."
            config.set(locationPath + "world", location.world?.name)
            config.set(locationPath + "x", location.x)
            config.set(locationPath + "y", location.y)
            config.set(locationPath + "z", location.z)
            config.set(locationPath + "type", entityType.name)
        }

        // Save the config to update the changes
        Dungeons().reloadConfig()
    }

    private fun findClosestLocation(playerLocation: Location, locations: MutableSet<Location>) = locations.minBy {
        it.distanceSquared(playerLocation)
    }.takeIf { it.distanceSquared(playerLocation) <= 25 }

    private fun String.toEntityTypeOrElse(default: EntityType) = runCatching { EntityType.valueOf(uppercase()) }.getOrElse { default }

}