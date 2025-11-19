package ie.setu.models

data class Match(
    var matchId: Int,
    val matchName: String,
    val matchLocation: String,
    val managerOnDuty: String
)
