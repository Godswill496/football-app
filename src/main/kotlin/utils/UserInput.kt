package ie.setu.utils

// Simple safe Int read, returns -1 if input is invalid
fun readIntNotNull() = readLine()?.toIntOrNull() ?: -1

// Reads Int and keeps prompting until user enters a valid number
fun readNextInt(prompt: String?): Int {
    do {
        try {
            print(prompt)
            return readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a number please.")
        }
    } while (true)
}

// Reads Double and keeps prompting until valid
fun readNextDouble(prompt: String?): Double {
    do {
        try {
            print(prompt)
            return readLine()!!.toDouble()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a valid decimal number.")
        }
    } while (true)
}

// Reads Float and keeps prompting until valid
fun readNextFloat(prompt: String?): Float {
    do {
        try {
            print(prompt)
            return readLine()!!.toFloat()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a valid float number.")
        }
    } while (true)
}

// Reads a full line of text
fun readNextLine(prompt: String?): String {
    print(prompt)
    return readLine()!!.trim()
}

// Reads a single character, keeps prompting if invalid
fun readNextChar(prompt: String?): Char {
    do {
        try {
            print(prompt)
            return readLine()!!.first()
        } catch (e: Exception) {
            System.err.println("\tEnter a character please.")
        }
    } while (true)
}
