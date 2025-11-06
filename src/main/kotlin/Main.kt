package ie.setu

import ie.setu.utils.readNextInt
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}


fun mainMenu(): Int {
        print("""
        > =============================
        > |      FOOTBALL APP         |
        > =============================
        > | MAIN MENU                 |
        > |   1) Add Player           |
        > |   2) Add Match            |
        > |   3) Record Player in Match|
        > |   4) List All Players      |
        > |   5) Match Summary         |
        > =============================
        > |   0) Exit                 |
        > =============================
        > ==> """.trimMargin(">"))
        return readNextInt(" > Enter option: ")
}

fun runMenu() {
        var choice: Int
        do {
                choice = mainMenu()
                when (choice) {
                        1 -> logger.info { "Add Player selected" }
                        2 -> logger.info { "Add Match selected" }
                        3 -> logger.info { "Record Player in Match selected" }
                        4 -> logger.info { "List All Players selected" }
                        5 -> logger.info { "Match Summary selected" }
                        0 -> logger.info { "Exiting Football App..." }
                        else -> logger.warn { "Invalid option entered: ${choice}" }
                }
        } while (choice != 0)
}

fun main() {
        logger.info { "Welcome to the Football App!" }
        runMenu()
}




