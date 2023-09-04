package com.tokyo.dungeons.util

import com.tokyo.dungeons.managers.Config
import com.tokyo.dungeons.serialisation.SpawnerLocation
import kotlin.random.Random
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.EntityEquipment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class CustomMob {
  private val world: World = Config.dungeonWorld!!.toWorld()!!

  fun spawnCustomMob(spawner: SpawnerLocation) {

    val entity: Entity = world.spawnEntity(spawner.getLocation(world), spawner.type)

    when (spawner.ability) {
      "Speed" -> {
        modifyAttribute(Attribute.GENERIC_MOVEMENT_SPEED, 0.5, entity, spawner)
      }
      "Health" -> {
        modifyAttribute(Attribute.GENERIC_MAX_HEALTH, 0.8, entity, spawner)
      }
      "Attack" -> {
        modifyAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 0.8, entity, spawner)
      }
      "Knockback" -> {
        modifyAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK, 0.6, entity, spawner)
      }
      "Easy" -> {
        modifyAttribute(Attribute.GENERIC_MOVEMENT_SPEED, 0.2, entity, spawner)
        modifyAttribute(Attribute.GENERIC_MAX_HEALTH, 0.2, entity, spawner)
        modifyAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 0.2, entity, spawner)
        modifyAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK, 0.2, entity, spawner)

        giveRandomArmor(entity as LivingEntity, "EASY")
      }
      "Medium" -> {
        modifyAttribute(Attribute.GENERIC_MOVEMENT_SPEED, 0.5, entity, spawner)
        modifyAttribute(Attribute.GENERIC_MAX_HEALTH, 0.5, entity, spawner)
        modifyAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 0.5, entity, spawner)
        modifyAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK, 0.5, entity, spawner)

        giveRandomArmor(entity as LivingEntity, "MEDIUM")
      }
      "Hard" -> {
        modifyAttribute(Attribute.GENERIC_MOVEMENT_SPEED, 0.8, entity, spawner)
        modifyAttribute(Attribute.GENERIC_MAX_HEALTH, 0.8, entity, spawner)
        modifyAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 0.8, entity, spawner)
        modifyAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK, 0.8, entity, spawner)
        giveRandomArmor(entity as LivingEntity, "HARD")
      }
    }
  }
}

private fun giveRandomArmor(entity: LivingEntity, armorType: String) {
  val equipment: EntityEquipment = entity.equipment!!

  for (slot in EquipmentSlot.values()) {
    if (slot.isHand) return

    // Map the armorType string to the corresponding enum value
    val armorPiece = ArmorPieces.values().find { it.name == armorType }

    if (armorPiece != null && Random.nextBoolean()) {
      // Set the armor piece for the current slot
      when (slot) {
        EquipmentSlot.HEAD -> equipment.helmet = ItemStack(Material.valueOf(armorPiece.head))
        EquipmentSlot.CHEST -> equipment.chestplate = ItemStack(Material.valueOf(armorPiece.chest))
        EquipmentSlot.LEGS -> equipment.leggings = ItemStack(Material.valueOf(armorPiece.legs))
        else -> equipment.boots = ItemStack(Material.valueOf(armorPiece.feet))
      }
    }
  }
}

fun modifyAttribute(
    attribute: Attribute,
    modifier: Double,
    entity: Entity,
    spawner: SpawnerLocation
) {
  val attributes = spawner.type.defaultAttributes

  val entityAttribute = attributes.getAttribute(attribute)
  val base = entityAttribute!!.baseValue
  entityAttribute.baseValue = base * modifier

  if (attribute == Attribute.GENERIC_MAX_HEALTH) {
    (entity as LivingEntity).health = base * modifier
  }
}

enum class ArmorPieces(val head: String, val chest: String, val legs: String, val feet: String) {
  EASY("LEATHER_HELMET", "LEATHER_CHESTPLATE", "LEATHER_LEGGINGS", "LEATHER_BOOTS"),
  MEDIUM("CHAINMAIL_HELMET", "CHAINMAIL_CHESTPLATE", "CHAINMAIL_LEGGINGS", "CHAINMAIL_BOOTS"),
  HARD("IRON_HELMET", "IRON_CHESTPLATE", "IRON_LEGGINGS", "IRON_BOOTS")
}
