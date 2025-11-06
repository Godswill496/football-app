package ie.setu


fun main() {
        var choice: Int

        do {
                println(
                        """
            |=============================
            | Football App - Main Menu
            |=============================
            | 1. Add Player
            | 2. Add Match
            | 3. Record Player in Match
            | 4. List All Players (by salary)
            | 5. Match Summary
            | 0. Exit
            |-----------------------------
            """.trimMargin("|")
                )
                print("> ")
                choice = readLine()?.trim()?.toIntOrNull() ?: -1

                when (choice) {
                        1 -> {
                                println("Add Player selected")
                                // Placeholder: will integrate PlayerController later
                        }
                        2 -> {
                                println("Add Match selected")
                                // Placeholder: will integrate MatchController later
                        }
                        3 -> {
                                println("Record Player in Match selected")
                                // Placeholder: will integrate MatchPlayerController later
                        }
                        4 -> {
                                println("List All Players selected")
                                // Placeholder: will integrate PlayerController later
                        }
                        5 -> {
                                println("Match Summary selected")
                                // Placeholder: will integrate MatchController & MatchPlayerController later
                        }
                        0 -> println("Exiting Football App...")
                        else -> println("Invalid choice, please try again.")
                }
        } while (choice != 0)
}


