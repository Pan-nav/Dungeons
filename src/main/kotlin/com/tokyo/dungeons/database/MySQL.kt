package com.tokyo.dungeons.database

import com.tokyo.dungeons.Dungeons
import com.tokyo.dungeons.managers.Config
import com.tokyo.dungeons.util.toUniqueId
import org.bukkit.Bukkit
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.UUID

object MySQL {

    private var database: MySQL? = null
    private var connection: Connection? = null
    private val mySQL = Config.mySQL
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


    fun loadCoins(uuid: String){
        val query : String = "SELECT Coins FROM Profiles WHERE UUID = ?"

        val preparedStatement = connection!!.prepareStatement(query)
        preparedStatement.setString(1, uuid)

        val rs = preparedStatement.executeQuery()
        while(rs.next()){
            val coins = rs.getInt("Coins")
            coinCache[uuid.toUniqueId()] = coins
        }
    }

    @Throws(SQLException::class)
    fun updateCoins(uuid: UUID){

        val coins = coinCache.getValue(uuid)

        Bukkit.getScheduler().runTaskAsynchronously(Dungeons.instance, Runnable {
            val query = """
                             INSERT INTO Profiles(UUID, Coins)
                             VALUES(?, ?)
                             ON DUPLICATE KEY UPDATE
                             Coins = ?
                             """.trimIndent()

                val statement = connection!!.prepareStatement(query)
                statement.setString(1, uuid.toString())
                statement.setInt(2, coins)
                statement.setInt(3, coins)
                statement.executeUpdate()

        })
    }

}