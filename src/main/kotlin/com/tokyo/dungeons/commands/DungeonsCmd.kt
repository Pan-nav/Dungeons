package com.tokyo.dungeons.commands

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.managers.DungeonManager
import com.tokyo.dungeons.runnables.Spawner
import com.tokyo.dungeons.util.toEntityTypeOrElse
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class DungeonsCmd : CommandExecutor {
  override fun onCommand(
      sender: CommandSender,
      command: Command,
      label: String,
      args: Array<out String>?
  ): Boolean {

    if (sender !is Player) {
      println("This command can only be used by a player.")
      return true
    }

    val player: Player = sender
    val dungeon = DungeonManager.getDungeon()

    if (args == null || args.isEmpty()) {
      player.sendMessage(
          "Please follow the format: /dungeons <addSource|removeSource|startDungeon|stopDungeon> [entityType] [abilityType] [Probability out of 100(eg: 20)]")
      return true
    }

    when (args[0]) {
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
      "addSource" -> {
        if (args.size < 2) {
          player.sendMessage("Please specify the entity type (e.g., /dungeons addSource Zombie)")
          return true
        }
        var ability = "None"
        var probability = 0
        val entityType: EntityType = args[1].toEntityTypeOrElse(EntityType.ZOMBIE)
        DungeonManager.saveDungeons()
        player.sendMessage("Source added at your location with the entity type: ${entityType.name}")

        if (args.size in 2..4) {
          ability = args[2]
          probability = args[3].toInt()
        }

        dungeon.addSpawner(player.location, entityType, ability, probability)
      }
      "startDungeon" -> {
        Spawner.runTaskTimer(Dungeons.instance, 0, 100)
      }
      "stopDungeon" -> {
        Spawner.cancel()
      }
      else -> {
        player.sendMessage(
            "Please follow the format: /dungeons <addSource|removeSource|startDungeon|stopDungeon> [entityType]")
      }
    }

    return true
  }
}
