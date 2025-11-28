package ie.setu.controllers

import ie.setu.models.MatchPlayer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MatchPlayerControllerTest {

    private var populatedMatchPlayerController: MatchPlayerController? = MatchPlayerController()
    private var emptyMatchPlayerController: MatchPlayerController? = MatchPlayerController()

    @BeforeEach
    fun setup() {
        populatedMatchPlayerController!!.addPlayerToMatch(
            MatchPlayer(0, 1, 10, "Striker", 2, true, 90)
        )
        populatedMatchPlayerController!!.addPlayerToMatch(
            MatchPlayer(0, 1, 11, "Winger", 1, true, 75)
        )
        populatedMatchPlayerController!!.addPlayerToMatch(
            MatchPlayer(0, 2, 10, "Striker", 0, false, 0)
        )
    }

    @AfterEach
    fun tearDown() {
        populatedMatchPlayerController = null
        emptyMatchPlayerController = null
    }

    @Nested
    inner class PlayedTests {

        @Test
        fun `listPlayedMatchPlayers returns only those who played`() {
            val result = populatedMatchPlayerController!!.listPlayedMatchPlayers()

            assertEquals(2, result.size)
            assertTrue(result.any { matchPlayer -> matchPlayer.playerId == 10 })
            assertTrue(result.any { matchPlayer -> matchPlayer.playerId == 11 })
        }

        @Test
        fun `numberOfPlayedMatchPlayers counts correctly`() {
            assertEquals(2, populatedMatchPlayerController!!.numberOfPlayedMatchPlayers())
        }
    }

    @Nested
    inner class NotPlayedTests {

        @Test
        fun `listNotPlayedMatchPlayers returns only non played`() {
            val result = populatedMatchPlayerController!!.listNotPlayedMatchPlayers()

            assertEquals(1, result.size)
            assertTrue(result.any { matchPlayer -> matchPlayer.playerId == 10 })
        }

        @Test
        fun `numberOfNotPlayedMatchPlayers counts correctly`() {
            assertEquals(1, populatedMatchPlayerController!!.numberOfNotPlayedMatchPlayers())
        }
    }

    @Nested
    inner class AddMatchPlayers {

        @Test
        fun `adding a matchplayer to a populated list adds correctly`() {
            val matchPlayer = MatchPlayer(0, 2, 12, "Midfielder", 0, false, 30)

            assertEquals(3, populatedMatchPlayerController!!.numberOfMatchPlayers())
            populatedMatchPlayerController!!.addPlayerToMatch(matchPlayer)
            assertEquals(4, populatedMatchPlayerController!!.numberOfMatchPlayers())
            assertEquals(matchPlayer, populatedMatchPlayerController!!.findMatchPlayer(3))
        }

        @Test
        fun `adding a matchplayer to an empty list adds correctly`() {
            val matchPlayer = MatchPlayer(0, 3, 20, "Defender", 0, false, 45)

            assertEquals(0, emptyMatchPlayerController!!.numberOfMatchPlayers())
            emptyMatchPlayerController!!.addPlayerToMatch(matchPlayer)
            assertEquals(1, emptyMatchPlayerController!!.numberOfMatchPlayers())
            assertEquals(matchPlayer, emptyMatchPlayerController!!.findMatchPlayer(0))
        }
    }

    @Nested
    inner class ListMatchPlayers {

        @Test
        fun `listPlayersInMatch returns empty when none exist`() {
            assertTrue(emptyMatchPlayerController!!.listPlayersInMatch(99).isEmpty())
        }

        @Test
        fun `listPlayersInMatch returns correct players`() {
            val result = populatedMatchPlayerController!!.listPlayersInMatch(1)

            assertEquals(2, result.size)
            assertTrue(result.any { matchPlayer -> matchPlayer.playerId == 10 })
            assertTrue(result.any { matchPlayer -> matchPlayer.playerId == 11 })
        }

        @Nested
        inner class DeleteMatchPlayers {

            @Test
            fun `deleting a MatchPlayer that does not exist returns null`() {
                assertNull(emptyMatchPlayerController!!.deleteMatchPlayer(0))
                assertNull(populatedMatchPlayerController!!.deleteMatchPlayer(-1))
            }

            @Test
            fun `deleting a MatchPlayer that exists deletes and returns deleted object`() {
                assertEquals(3, populatedMatchPlayerController!!.numberOfMatchPlayers())

                val deleted = populatedMatchPlayerController!!.deleteMatchPlayer(1)
                assertNotNull(deleted)
                assertEquals(2, populatedMatchPlayerController!!.numberOfMatchPlayers())
            }
        }

    }
}

