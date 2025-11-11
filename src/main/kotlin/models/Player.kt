package models

data class Player(
    val playerId: Int,
    val dateOfBirth: String,
    val name: String,
    val shirtNo: Int,
    val salary: Double,
    val preferredPlayPosition: String,
    val isInjured: Boolean
)

