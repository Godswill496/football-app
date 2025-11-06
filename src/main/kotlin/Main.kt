package ie.setu


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
        return readLine()?.toIntOrNull() ?: -1
}

fun runMenu() {
        var choice: Int
        do {
                choice = mainMenu()
                when (choice) {
                        1 -> println("Add Player selected")
                        2 -> println("Add Match selected")
                        3 -> println("Record Player in Match selected")
                        4 -> println("List All Players selected")
                        5 -> println("Match Summary selected")
                        0 -> println("Exiting Football App...")
                        else -> println("Invalid option entered: ${choice}")
                }
        } while (choice != 0)
}

fun main() {
        println("Welcome to the Football App!")
        runMenu()
}



