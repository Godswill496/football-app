package ie.setu

import ie.setu.controllers.MatchController
import ie.setu.controllers.MatchPlayerController
import ie.setu.controllers.PlayerController
import ie.setu.utils.readNextInt
import java.lang.System.exit
import io.github.oshai.kotlinlogging.KotlinLogging



private val logger = KotlinLogging.logger {}
private val playerController = PlayerController()
private val matchController = MatchController()
private val matchPlayerController = MatchPlayerController()





fun mainMenu(): Int {
        print(
                """
        > ------------------------------------------
        > |          FOOTBALL APP           |
        > ------------------------------------------
        > | MENU                                     |
        > |   1) Add Player                          |
        > |   2) List All Players                    |
        > |   3) Add Match                           |
        > |   4) List Matches                        |
        > |   5) Add Player to Match                 |
        > |   6) List Match Players                  |
        > ------------------------------------------
        > |   0) Exit                                |
        > ------------------------------------------
        > ==>> 
        """.trimMargin(">")
        )
        return readNextInt(" > ==>> ")
}



fun runMenu() {
        do {
                val option = mainMenu()
                when (option) {
                        1 -> addPlayer()
                        2 -> listPlayers()
                        3 -> addMatch()
                        4 -> listMatches()
                        5 -> addPlayerToMatch()
                        6 -> listMatchPlayers()
                        0 -> exitApp()
                        else -> println("Invalid option entered: ${option}")
                }
        } while (true)
}



fun addPlayer() {
        logger.info { "addPlayer() function invoked" }
}

fun listPlayers() {
        logger.info { "listPlayers() function invoked" }
}

fun addMatch() {
        logger.info { "addMatch() function invoked" }
}

fun listMatches() {
        logger.info { "listMatches() function invoked" }
}

fun addPlayerToMatch() {
        logger.info { "addPlayerToMatch() function invoked" }
}

fun listMatchPlayers() {
        logger.info { "listMatchPlayers() function invoked" }
}


fun exitApp() {
        logger.info { "exitApp() function invoked — exiting program" }
        exit(0)
}




fun main() {
        runMenu()
}







