package com.tokyo.dungeons.type.customItem

import me.superpenguin.superglue.foundations.util.ItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import kotlin.time.times

data class DungeonItemBuilder(
    val type: Material,
    val name: String,
    var defaultStats: Stats,
    var rarity: Rarity = Rarity.COMMON,
    var reforge: Reforge?,
    val amount : Int = 1,
) {
    fun build(): ItemStack {
        val reforgeStats = reforge?.stats ?: Stats()

        return ItemBuilder(type, "${reforge?.name ?: " "} $name", amount).apply {
            lore(
                """
                    Strength: ${defaultStats.strength * reforgeStats.strength}
                    Crit Chance: ${defaultStats.critChance * reforgeStats.critChance}
                    Crit Damage: ${defaultStats.critDamage * reforgeStats.critDamage}
                    Attack Speed: ${defaultStats.attackSpeed * reforgeStats.attackSpeed}
                    Mana: ${defaultStats.mana * reforgeStats.mana}
                    Luck: ${defaultStats.luck * reforgeStats.luck}
                    Defense: ${defaultStats.defense * reforgeStats.defense}
                    Health: ${defaultStats.health * reforgeStats.health}
                    Speed: ${defaultStats.speed * reforgeStats.speed}
                    
                    
                """.trimIndent().split("\n")
            )
        }.build()

    }
}