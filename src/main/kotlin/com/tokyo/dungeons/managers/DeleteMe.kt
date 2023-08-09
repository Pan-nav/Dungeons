//package com.tokyo.dungeons.managers
//
//import com.tokyo.dungeons.Dungeons
//import org.bukkit.configuration.file.FileConfiguration
//
//object ConfigManager {
//
//    private lateinit var config: FileConfiguration
//    lateinit var mySQL : MySQL
//
//    fun setupConfig(){
//        config = Dungeons.instance.config
//        Dungeons.instance.saveDefaultConfig()
//        mySQL = MySQL("HOST".getString(), "PORT".getInt(),"USERNAME".getString(),"DATABASE".getString(), "PASS".getString() )
//    }
//
//    fun getDungeonsWorld() = config.getString("Dungeon-World")
//
//    fun String.getString() = Dungeons.instance.config.getString(this)
//    fun String.getInt() = Dungeons.instance.config.getInt(this)
//
//    data class MySQL(val host: String?, val port: Int?, val username: String?, val database: String?, val pass: String?)
//}