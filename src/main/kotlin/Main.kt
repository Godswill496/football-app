package ie.setu

import ie.setu.controllers.MatchController
import ie.setu.controllers.MatchPlayerController
import ie.setu.controllers.PlayerController
import ie.setu.models.Player
import ie.setu.persistence.JSONSerializer

import ie.setu.utils.readNextDouble
import ie.setu.utils.readNextInt
import ie.setu.utils.readNextLine
import ie.setu.utils.formatPlayerList

import java.lang.System.exit
import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File


private val logger = KotlinLogging.logger {}

private val playerController =
        PlayerController(JSONSerializer(File("players.json")))

private val matchController =
        MatchController(JSONSerializer(File("matches.json")))

private val matchPlayerController =
        MatchPlayerController(JSONSerializer(File("matchplayers.json")))


fun mainMenu(): Int {
        print(
                """
        > ------------------------------------------
        > |               FOOTBALL APP              |
        > ------------------------------------------
        > | MENU                                     |
        > |   1) Add Player                          |
        > |   2) List Players Menu                   |
        > |   3) Add Match                           |
        > |   4) List Matches                        |
        > |   5) Add Player to Match                 |
        > |   6) List Match Players                  |
        > |   7) Delete Player                       |
        > |   8) Update Player                       |
        > |   9) Save All Data                       |
        > |  10) Load All Data                       |
        > |  11) Archive Player                      |
        > ------------------------------------------
        > |   0) Exit                                |
        > ------------------------------------------
        > ==>> 
        """.trimMargin(">")
        )
        return readNextInt("Enter Option: ")
}


fun runMenu() {
        do {
                when (val option = mainMenu()) {
                        1 -> addPlayer()
                        2 -> listPlayersMenu()
                        3 -> addMatch()
                        4 -> listMatches()
                        5 -> addPlayerToMatch()
                        6 -> listMatchPlayers()
                        7 -> deletePlayer()
                        8 -> updatePlayer()
                        9 -> saveAll()
                        10 -> loadAll()
                        11 -> archivePlayer()
                        0 -> exitApp()
                        else -> println("Invalid option entered: $option")
                }
        } while (true)
}


fun listPlayersMenu() {
        println(
                """
        |----------------------------
        |      LIST PLAYERS MENU
        |----------------------------
        | 1) List ALL players
        | 2) List FIT players
        | 3) List INJURED players
        | 4) List PLAYERS by SALARY (Low to High)
        |----------------------------
        | 0) Back to Main Menu
        |----------------------------
        """.trimMargin()
        )

        when (readNextInt("Select option: ")) {
                1 -> println(formatPlayerList(playerController.listPlayers()))
                2 -> println(playerController.listFitPlayers())
                3 -> println(playerController.listInjuredPlayers())
                4 -> println(formatPlayerList(playerController.listPlayersBySalaryIncreasing()))
                0 -> return
                else -> println("Invalid option")
        }
}



fun addPlayer() {

        val name = readNextLine("Enter player name: ")
        val dob = readNextLine("Enter date of birth (YYYY-MM-DD): ")
        val shirtNo = readNextInt("Enter shirt number: ")
        val salary = readNextDouble("Enter salary: ")
        val pos = readNextLine("Enter preferred playing position: ")
        val injured = readNextLine("Is the player injured (yes/no): ").toBoolean()

        val player = Player(
                playerId = 0,
                name = name,
                dateOfBirth = dob,
                shirtNo = shirtNo,
                salary = salary,
                preferredPlayPosition = pos,
                isInjured = injured
        )

        playerController.addPlayer(player)
        println("Player Added Successfully!")
}


fun deletePlayer() {
        println(formatPlayerList(playerController.listPlayers()))

        if (playerController.numberOfPlayers() > 0) {
                val index = readNextInt("Enter index of player to delete: ")
                val removed = playerController.deletePlayer(index)

                if (removed != null)
                        println("Deleted Player")
                else
                        println("Delete Failed")
        }
}


fun updatePlayer() {
        println(formatPlayerList(playerController.listPlayers()))

        if (playerController.numberOfPlayers() > 0) {
                val id = readNextInt("Enter index of player to update: ")

                if (playerController.findPlayer(id) != null) {
                        val name = readNextLine("Enter new name: ")
                        val dob = readNextLine("Enter new DOB: ")
                        val shirtNo = readNextInt("Enter new shirt number: ")
                        val salary = readNextDouble("Enter new salary: ")
                        val pos = readNextLine("Enter new position: ")
                        val injured = readNextLine("Injured (yes/no): ").toBoolean()

                        val updated = Player(
                                playerId = id,
                                name = name,
                                dateOfBirth = dob,
                                shirtNo = shirtNo,
                                salary = salary,
                                preferredPlayPosition = pos,
                                isInjured = injured
                        )

                        if (playerController.updatePlayer(id, updated))
                                println("Update Successful!")
                        else
                                println("Update Failed.")
                } else {
                        println("Invalid Player Index")
                }
        }
}


fun archivePlayer() {
        println(playerController.listFitPlayersWithIndex())

        if (playerController.numberOfFitPlayers() > 0) {
                val id = readNextInt("Enter index of player to archive: ")

                if (playerController.archivePlayer(id))
                        println("Archive Successful! Player marked as injured.")
                else
                        println("Archive Failed.")
        }
}


fun addMatch() {

        val name = readNextLine("Enter match name: ")
        val loc = readNextLine("Enter match location: ")
        val manager = readNextLine("Enter manager on duty: ")

        matchController.addMatch(
                ie.setu.models.Match(
                        matchId = 0,
                        matchName = name,
                        matchLocation = loc,
                        managerOnDuty = manager
                )
        )

        println("Match Added Successfully!")
}


fun listMatches() {
        println(matchController.listMatches())
}


fun addPlayerToMatch() {

        val matchId = readNextInt("Enter Match ID: ")
        val playerId = readNextInt("Enter Player ID: ")
        val pos = readNextLine("Enter play position: ")
        val goals = readNextInt("Enter number of goals: ")
        val played = readNextLine("Did player play (true/false): ").toBoolean()
        val mins = readNextInt("Enter minutes played: ")

        matchPlayerController.addPlayerToMatch(
                ie.setu.models.MatchPlayer(
                        id = 0,
                        matchId = matchId,
                        playerId = playerId,
                        playPosition = pos,
                        goalScore = goals,
                        isPlayed = played,
                        numMinsPlayed = mins
                )
        )

        println("Player Added to Match!")
}


fun listMatchPlayers() {
        val matchId = readNextInt("Enter match ID: ")
        val mp = matchPlayerController.listPlayersInMatch(matchId)

        if (mp.isEmpty())
                println("No players recorded for match $matchId.")
        else
                println(mp)
}


fun saveAll() {
        try {
                playerController.store()
                matchController.store()
                matchPlayerController.store()
                println("All data saved successfully.")
        } catch (e: Exception) {
                System.err.println("Error saving: $e")
        }
}

fun loadAll() {
        try {
                playerController.load()
                matchController.load()
                matchPlayerController.load()
                println("All data loaded successfully.")
        } catch (e: Exception) {
                System.err.println("Error loading: $e")
        }
}


fun exitApp() {
        logger.info { "Exit" }
        exit(0)
}


fun main() {
        runMenu()
}








