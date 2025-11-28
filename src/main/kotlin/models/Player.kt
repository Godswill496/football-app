package ie.setu.models

data class Player(
    var playerId: Int,
    var dateOfBirth: String,
    var name: String,
    var shirtNo: Int,
    var salary: Double,
    var preferredPlayPosition: String,
    var isInjured: Boolean
)

