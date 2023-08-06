package com.tokyo.dungeons

import com.tokyo.dungeons.commands.DungeonsCmd
import com.tokyo.dungeons.events.EntityKill
import com.tokyo.dungeons.managers.DungeonManager
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Dungeons : JavaPlugin() {

    companion object {
        lateinit var instance: Dungeons
    }

    override fun onEnable() {
        instance = this

        DungeonManager

        getCommand("dungeons")?.setExecutor(DungeonsCmd())

        EntityKill().register()
    }

    private fun Listener.register() = Bukkit.getPluginManager().registerEvents(this, this@Dungeons)

}