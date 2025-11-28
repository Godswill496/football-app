package ie.setu.controllers

import ie.setu.models.Match
import ie.setu.utils.formatPlayerList


class MatchController {

    private val matches = mutableListOf<Match>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addMatch(match: Match) {
        match.matchId = getId()
        matches.add(match)
    }

    fun listMatches(): List<Match> {
        return matches
}
fun numberOfMatches(): Int {
    return matches.size
}

fun findMatch(index: Int): Match? {
    return if (isValidListIndex(index, matches)) {
        matches[index]
    } else null
}


fun isValidListIndex(index: Int, list: List<Any>): Boolean {
    return (index >= 0 && index < list.size)
}}

