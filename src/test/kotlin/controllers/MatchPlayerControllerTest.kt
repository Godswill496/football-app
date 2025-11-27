package ie.setu.controllers

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ie.setu.models.MatchPlayer

class MatchPlayerControllerTest {

    private var populatedMP: MatchPlayerController? = MatchPlayerController()
    private var emptyMP: MatchPlayerController? = MatchPlayerController()

    @BeforeEach
    fun setup() {
        populatedMP!!.addPlayerToMatch(MatchPlayer(0, 1, 10, "Striker", 2, true, 90))
        populatedMP!!.addPlayerToMatch(MatchPlayer(0, 1, 11, "Winger", 1, true, 75))
        populatedMP!!.addPlayerToMatch(MatchPlayer(0, 2, 10, "Striker", 0, false, 0))
    }

    @AfterEach
    fun tearDown() {
        populatedMP = null
        emptyMP = null
    }

    @Nested
    inner class AddMatchPlayers {

        @Test
        fun `adding a matchplayer to a populated list adds correctly`() {
            val mp = MatchPlayer(0, 2, 12, "Midfielder", 0, false, 30)

            assertEquals(3, populatedMP!!.numberOfMatchPlayers())
            populatedMP!!.addPlayerToMatch(mp)
            assertEquals(4, populatedMP!!.numberOfMatchPlayers())
            assertEquals(mp, populatedMP!!.findMatchPlayer(3))
        }

        @Test
        fun `adding a matchplayer to an empty list adds correctly`() {
            val mp = MatchPlayer(0, 3, 20, "Defender", 0, false, 45)

            assertEquals(0, emptyMP!!.numberOfMatchPlayers())
            emptyMP!!.addPlayerToMatch(mp)
            assertEquals(1, emptyMP!!.numberOfMatchPlayers())
            assertEquals(mp, emptyMP!!.findMatchPlayer(0))
        }
    }

    @Nested
    inner class ListMatchPlayers {

        @Test
        fun `listPlayersInMatch returns empty when none exist`() {
            assertTrue(emptyMP!!.listPlayersInMatch(99).isEmpty())
        }

        @Test
        fun `listPlayersInMatch returns correct players`() {
            val result = populatedMP!!.listPlayersInMatch(1)

            assertEquals(2, result.size)
            assertTrue(result.any { it.playerId == 10 })
            assertTrue(result.any { it.playerId == 11 })
        }
    }
}
