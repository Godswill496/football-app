package ie.setu

import ie.setu.controllers.MatchController
import ie.setu.controllers.MatchPlayerController
import ie.setu.controllers.PlayerController
import ie.setu.models.Player
import ie.setu.utils.readNextDouble
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextLine
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
        > |   6) List Match Players 
        >     7) Delete Player  
        >     8) Update Player                     |
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
                        7 -> deletePlayer()
                        8 -> updatePlayer()
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

fun deletePlayer() {

        listPlayers()
        if (playerController.numberOfPlayers() > 0) {


                val indexToDelete = readNextInt("Enter the index of the player to delete: ")


                val playerToDelete = playerController.deletePlayer(indexToDelete)

                if (playerToDelete != null) {
                        println("Delete Successful! Deleted player: ${playerToDelete.name}")
                } else {
                        println("Delete NOT Successful")
                }
        }
}

fun updatePlayer() {
        listPlayers()

        if (playerController.numberOfPlayers() > 0) {

                val idToUpdate = readNextInt("Enter ID of the player to update: ")

                if (playerController.findPlayer(idToUpdate) != null) {

                        val name = readNextLine("Enter new name: ")
                        val dob = readNextLine("Enter new date of birth: ")
                        val shirtNo = readNextInt("Enter new shirt number: ")
                        val salary = readNextDouble("Enter new salary: ")
                        val pos = readNextLine("Enter preferred play position: ")
                        val injured = readNextLine("Is player injured (true/false): ").toBoolean()

                        val updated = Player(
                                playerId = idToUpdate,
                                name = name,
                                dateOfBirth = dob,
                                shirtNo = shirtNo,
                                salary = salary,
                                preferredPlayPosition = pos,
                                isInjured = injured
                        )

                        if (playerController.updatePlayer(idToUpdate, updated))
                                println("Update Successful")
                        else
                                println("Update Failed")

                } else {
                        println("Invalid Player ID")
                }
        }
}



fun exitApp() {
        logger.info { "exitApp() function invoked — exiting program" }
        exit(0)
}




fun main() {
        runMenu()
}







