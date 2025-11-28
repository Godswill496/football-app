package ie.setu.utils

import ie.setu.models.Player
import ie.setu.models.MatchPlayer
import ie.setu.models.Match


fun <T> formatListString(listToFormat: List<T>): String =
    listToFormat
        .joinToString(separator = "\n") { item -> "$item" }


fun formatPlayerList(playersToFormat: List<Player>): String =
    playersToFormat.joinToString(separator = "\n") { player -> "$player" }

fun formatMatchList(matchesToFormat: List<Match>): String =
    matchesToFormat.joinToString(separator = "\n") { match -> "$match" }

fun formatMatchPlayerList(matchPlayerToFormat:  List<MatchPlayer>): String =
    matchPlayerToFormat.joinToString(separator = "\n") { matchplayer -> "$matchplayer" }


fun readIntNotNull() = readlnOrNull()?.toIntOrNull() ?: -1

fun readNextInt(prompt: String?): Int {
    do {
        try {
            print(prompt)
            return readln().toInt()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a number please.")
        }
    } while (true)
}

fun readNextDouble(prompt: String?): Double {
    do {
        try {
            print(prompt)
            return readln().toDouble()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a valid decimal number please.")
        }
    } while (true)
}

fun readNextFloat(prompt: String?): Float {
    do {
        try {
            print(prompt)
            return readln().toFloat()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a valid floating number please.")
        }
    } while (true)
}

fun readNextLine(prompt: String?): String {
    print(prompt)
    return readln()
}

fun readNextChar(prompt: String?): Char {
    do {
        try {
            print(prompt)
            return readln().first()
        } catch (e: Exception) {
            System.err.println("\tEnter a character please.")
        }
    } while (true)
}



