package com.tokyo.dungeons.commands

import com.tokyo.dungeons.ConfigManager
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DungeonsCMD : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (sender !is Player){
            println("This command can only be used by a player.")
            return true
        }

        val player : Player = sender

        if (args == null) {
            player.sendMessage("Please follow the format: /dungeons sourcePrompt")
            return true
        }

        if (args[0] == "removeSource"){
            val loc = findClosestLocation(player.location, ConfigManager.spawners.keys)
            
        }

        return true
    }


    private fun findClosestLocation(playerLocation: Location, locations: MutableSet<Location>): Location? {
        var closestLocation: Location? = null
        var minDistanceSquared = 25.0

        for (location in locations) {
            // Calculate the squared distance (faster than calculating the actual distance)
            val distanceSquared = playerLocation.distanceSquared(location)

            // Check if the location is within the specified radius
            if (distanceSquared <= minDistanceSquared) {
                closestLocation = location
                minDistanceSquared = distanceSquared
            }
        }

        return closestLocation
    }

}