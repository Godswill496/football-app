package ie.setu.controllers

import ie.setu.models.MatchPlayer

class MatchPlayerController {

    private var matchPlayers = ArrayList<MatchPlayer>()
    private var lastId = 0
    private fun getNextId() = lastId++

    fun addPlayerToMatch(matchPlayer: MatchPlayer): Boolean {
        matchPlayer.id = getNextId()
        return matchPlayers.add(matchPlayer)
    }

    fun listPlayersInMatch(matchId: Int): List<MatchPlayer> {
        return matchPlayers.filter { it.matchId == matchId }
    }

    fun numberOfMatchPlayers(): Int {
        return matchPlayers.size
    }

    fun findMatchPlayer(index: Int): MatchPlayer? {
        return if (isValidListIndex(index, matchPlayers)) {
            matchPlayers[index]
        } else null
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}


