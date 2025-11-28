package ie.setu.controllers

import ie.setu.models.Player
import ie.setu.utils.formatPlayerList


class PlayerController {

    private val players = mutableListOf<Player>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addPlayer(player: Player) {
        player.playerId = getId()
        players.add(player)
    }

    fun listPlayers() = players

    fun listPlayersBySalaryIncreasing(): List<Player> {
        return players.sortedBy { player -> player.salary }
    }


    fun numberOfPlayers(): Int {
        return players.size
    }

    fun findPlayer(index: Int): Player? {
        return if (isValidListIndex(index, players)) {
            players[index]
        } else null
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun listFitPlayers(): String {
        return if (numberOfFitPlayers() == 0) {
            "No fit players stored"
        } else {
            formatPlayerList(players.filter { player -> !player.isInjured })
        }
    }


    fun listInjuredPlayers(): String =
        if (numberOfInjuredPlayers() == 0) "No injured players stored"
        else formatPlayerList(players.filter { player -> player.isInjured })


    fun numberOfFitPlayers(): Int =
        players.count { player -> !player.isInjured }


    fun numberOfInjuredPlayers(): Int {
        return players.count { player -> player.isInjured }
    }

    fun deletePlayer(id: Int): Player? {
        return if (isValidListIndex(id, players)) {
            players.removeAt(id)
        } else null
    }

    fun updatePlayer(id: Int, updatedPlayer: Player?): Boolean {
        val foundPlayer = findPlayer(id)

        if (foundPlayer != null && updatedPlayer != null) {
            foundPlayer.name = updatedPlayer.name
            foundPlayer.dateOfBirth = updatedPlayer.dateOfBirth
            foundPlayer.salary = updatedPlayer.salary
            foundPlayer.preferredPlayPosition = updatedPlayer.preferredPlayPosition
            foundPlayer.shirtNo = updatedPlayer.shirtNo
            foundPlayer.isInjured = updatedPlayer.isInjured
            return true
        }
        return false
    }




}

