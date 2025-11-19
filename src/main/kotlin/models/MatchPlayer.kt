package ie.setu.models

data class MatchPlayer(
    var id: Int,
    val matchId: Int,
    val playerId: Int,
    val playPosition: String,
    val goalScore: Int,
    val isPlayed: Boolean,
    val numMinsPlayed: Int
)
