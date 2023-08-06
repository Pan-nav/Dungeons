package com.tokyo.dungeons.commands

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.Runnable
import com.tokyo.dungeons.managers.DungeonManager
import com.tokyo.dungeons.toEntityTypeOrElse
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
        val dungeon = DungeonManager.getDungeon()

        if (args == null || args.isEmpty()) {
            player.sendMessage("Please follow the format: /dungeons <addSource|removeSource|startDungeon|stopDungeon> [entityType]")
            return true
        }

        when(args[0]){

            "removeSource" -> {
                val closestLocation = dungeon.getClosestSpawnerPos(player.location, 5)
                if (closestLocation != null) {
                    dungeon.spawnPositions.remove(closestLocation)
                    DungeonManager.saveDungeons()
                    player.sendMessage("Closest Location removed.")
                } else {
                    player.sendMessage("No nearby location found.")
                }
            }

            "addSource" ->{
                if (args.size < 2) {
                    player.sendMessage("Please specify the entity type (e.g., /dungeons addSource Zombie)")
                    return true
                }
                val entityType: EntityType = args[1].toEntityTypeOrElse(EntityType.ZOMBIE)
                dungeon.addSpawner(player.location, entityType)
                DungeonManager.saveDungeons()
                player.sendMessage("Source added at your location with the entity type: ${entityType.name}")
            }

            "startDungeon" -> {
                Runnable.runTaskTimer(Dungeons.instance, 0, 100)}
            "stopDungeon"  -> {
                Runnable.cancel()}
            else -> {player.sendMessage("Please follow the format: /dungeons <addSource|removeSource|startDungeon|stopDungeon> [entityType]")}
        }

        return true
    }

}