package ie.setu.utils

fun readIntNotNull() = readLine()?.toIntOrNull() ?: -1

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

fun readNextLine(prompt: String?): String {
    print(prompt)
    return readLine()!!.trim()
}

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

