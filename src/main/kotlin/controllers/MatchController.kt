package ie.setu.controllers

import ie.setu.models.Match


class MatchController {

    private val matches = mutableListOf<Match>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addMatch(match: Match) {
        match.matchId = getId()
        matches.add(match)
    }

    fun listMatches() = matches
}
