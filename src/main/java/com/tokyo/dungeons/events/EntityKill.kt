package com.tokyo.dungeons.events

import com.tokyo.dungeons.managers.ConfigManager
import com.tokyo.dungeons.toWorld
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityKill : Listener {

    @EventHandler
    fun onKill(e: EntityDeathEvent){
        if (e.entity.world != ConfigManager.getDungeonsWorld()!!.toWorld()) return
        //add a random probability to drop a custom shard.
    }

}