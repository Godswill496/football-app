package ie.setu.controllers

import ie.setu.models.MatchPlayer

class MatchPlayerController {

    private val matchPlayers = ArrayList<MatchPlayer>()
    private var lastId = 0
    private fun getNextId() = lastId++

    fun addPlayerToMatch(matchPlayer: MatchPlayer): Boolean {
        matchPlayer.id = getNextId()
        return matchPlayers.add(matchPlayer)
    }


    fun listPlayersInMatch(matchId: Int): List<MatchPlayer> =
        matchPlayers.filter { matchPlayer -> matchPlayer.matchId == matchId }

    fun listPlayedMatchPlayers(): List<MatchPlayer> =
        matchPlayers.filter { matchPlayer -> matchPlayer.isPlayed }

    fun listNotPlayedMatchPlayers(): List<MatchPlayer> =
        matchPlayers.filter { matchPlayer -> !matchPlayer.isPlayed }


    fun numberOfMatchPlayers(): Int =
        matchPlayers.size

    fun numberOfPlayedMatchPlayers(): Int =
        matchPlayers.count { matchPlayer -> matchPlayer.isPlayed }

    fun numberOfNotPlayedMatchPlayers(): Int =
        matchPlayers.count { matchPlayer -> !matchPlayer.isPlayed }


    fun findMatchPlayer(index: Int): MatchPlayer? =
        if (isValidListIndex(index, matchPlayers)) matchPlayers[index] else null

    fun isValidListIndex(index: Int, list: List<Any>): Boolean =
        (index >= 0 && index < list.size)


fun deleteMatchPlayer(id: Int): MatchPlayer? {
    return if (isValidListIndex(id, matchPlayers)) {
        matchPlayers.removeAt(id)
    } else null
}}





















