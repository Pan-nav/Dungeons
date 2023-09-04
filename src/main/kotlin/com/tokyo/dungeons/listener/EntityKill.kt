package com.tokyo.dungeons.listener

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.managers.Config
import com.tokyo.dungeons.util.toWorld
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.random.Random

class EntityKill : Listener {

    private val coinCache = Dungeons.coinCache

    @EventHandler
    fun onKill(e: EntityDeathEvent){
        if (e.entity.world != Config.dungeonWorld!!.toWorld()) return

        val player = e.entity.killer

        if (player != null){
            val playerId = player.uniqueId

            val coins = coinCache.getOrDefault(playerId, 0)
            val earnt = Random.nextInt(3)+1
            coinCache[playerId] = coins+earnt

            if (Random.nextInt(50)==0){
                val shard = ItemStack(Material.PRISMARINE_SHARD)
                val meta = shard.itemMeta
                meta.displayName(Component.text("Shard")
                    .color(NamedTextColor.AQUA)
                    .decorate(TextDecoration.BOLD))
                meta.persistentDataContainer.set(Dungeons.key, PersistentDataType.BOOLEAN, true)
                shard.itemMeta = meta
                player.inventory.addItem(shard)
                player.sendMessage(Component.text("You just found a Shard!")
                    .color(NamedTextColor.AQUA)
                    )
            }
        }

    }

}