package ie.setu.controllers

import ie.setu.models.MatchPlayer


class MatchPlayerController {

    private val matchPlayers = mutableListOf<MatchPlayer>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addPlayerToMatch(matchPlayer: MatchPlayer) {
        matchPlayer.id = getId()
        matchPlayers.add(matchPlayer)
    }

    fun listPlayersInMatch(matchId: Int) =
        matchPlayers.filter { it.matchId == matchId }
}
