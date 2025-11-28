package ie.setu.controllers

import ie.setu.models.Match
import ie.setu.persistence.Serializer


class MatchController (serializerType: Serializer) {

    private var serializer: Serializer = serializerType
    private var matches = mutableListOf<Match>()
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
}

fun deleteMatch(id: Int): Match? {
    return if (isValidListIndex(id, matches)) {
        matches.removeAt(id)
    } else null
}
    @Throws(Exception::class)
    fun load() {
        matches = serializer.read() as ArrayList<Match>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(matches)
    }}


