package com.tokyo.dungeons.listener

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.database.MySQL
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerListener: Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent){
        MySQL.loadCoins(e.player.uniqueId.toString())
    }

    @EventHandler
    fun onLeave(e: PlayerQuitEvent){
        MySQL.updateCoins(e.player.uniqueId)
        Dungeons.coinCache.remove(e.player.uniqueId)
    }
}