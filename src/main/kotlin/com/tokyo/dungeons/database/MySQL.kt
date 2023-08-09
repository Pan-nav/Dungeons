package com.tokyo.dungeons.database

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.managers.ConfigManager
import com.tokyo.dungeons.toUniqueId
import org.bukkit.Bukkit
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object MySQL {

    private var database: MySQL? = null
    private var connection: Connection? = null
    private val mySQL = ConfigManager.mySQL
    private val coinCache = Dungeons.coinCache
    @Throws(SQLException::class)
    fun connect() {

        database = this
        connection = DriverManager.getConnection(
            "jdbc:mysql://${mySQL.host}:${mySQL.port}/${mySQL.database}?useSSL=false&autoReconnect=true",
            mySQL.username,
            mySQL.pass
        )
        createTable()
    }

    private fun isConnected(): Boolean {
        return connection != null
    }

    fun getConnection(): Connection {
        return connection!!
    }

    fun disconnect() {
        if (isConnected()) {
            try {
                connection!!.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }

    private fun createTable() {
        try {
            val statement = connection!!.createStatement()
            statement.executeUpdate("""
                                    CREATE TABLE IF NOT EXISTS Profiles (
                                    UUID varchar(36),
                                    Coins int(16),
                                    EXP int(255),
                                    IPs varchar(255));
                                    """.trimIndent())

        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }


    fun loadCoins(){
        val query : String = "SELECT UUID, Coins FROM Profiles"
        run {
            val statement = connection!!.createStatement()
            val rs = statement.executeQuery(query)

            while(rs.next()){
                val uuid = rs.getString("UUID")
                val coins = rs.getInt("Coins")
                coinCache[uuid.toUniqueId()] = coins
            }
        }
    }

    @Throws(SQLException::class)
    fun updateCoins(uuid: String, coins: Int){

        Bukkit.getScheduler().runTaskAsynchronously(Dungeons.instance, Runnable {
            val query = """
                             INSERT INTO Profiles(UUID, Coins)
                             VALUES(?, ?)
                             ON DUPLICATE KEY UPDATE
                             Coins = ?
                             """.trimIndent()
            run {
                val statement = connection!!.prepareStatement(query)
                statement.setString(1, uuid)
                statement.setInt(2, coins)
                statement.setInt(3, coins)
                statement.executeUpdate()
            }
        })
    }

}