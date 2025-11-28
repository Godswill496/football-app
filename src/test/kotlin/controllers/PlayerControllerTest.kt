package ie.setu.controllers


import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ie.setu.models.Player
import kotlin.test.assertFalse

class PlayerControllerTest {

        private var ronaldo: Player? = null
        private var messi: Player? = null
        private var mbappe: Player? = null

        private var populatedPlayers: PlayerController? = PlayerController()
        private var emptyPlayers: PlayerController? = PlayerController()

        @BeforeEach
        fun setup() {
            ronaldo = Player(0, "1985-02-05", "Ronaldo", 7, 500000.0, "Striker", false)
            messi   = Player(0, "1987-06-24", "Messi", 10, 600000.0, "Forward", false)
            mbappe  = Player(0, "1998-12-20", "Mbappe", 7, 700000.0, "Forward", false)

            populatedPlayers!!.addPlayer(ronaldo!!)
            populatedPlayers!!.addPlayer(messi!!)
            populatedPlayers!!.addPlayer(mbappe!!)
        }

        @AfterEach
        fun tearDown() {
            ronaldo = null
            messi = null
            mbappe = null
            populatedPlayers = null
            emptyPlayers = null
        }

        @Nested
        inner class FitPlayers {

            @Test
            fun `listFitPlayers returns players who are not injured`() {
                val result = populatedPlayers!!.listFitPlayers()
                assertTrue(result.contains("Ronaldo"))
                assertTrue(result.contains("Mbappe"))
                assertFalse(result.contains("Messi"))
            }

            @Test
            fun `numberOfFitPlayers counts correctly`() {
                assertEquals(2, populatedPlayers!!.numberOfFitPlayers())
                assertEquals(0, emptyPlayers!!.numberOfFitPlayers())
            }
        }

        @Nested
        inner class InjuredPlayers {

            @Test
            fun `listInjuredPlayers returns only injured players`() {
                val result = populatedPlayers!!.listInjuredPlayers()
                assertTrue(result.contains("Messi"))
                assertFalse(result.contains("Ronaldo"))
            }

            @Test
            fun `numberOfInjuredPlayers counts correctly`() {
                assertEquals(1, populatedPlayers!!.numberOfInjuredPlayers())
                assertEquals(0, emptyPlayers!!.numberOfInjuredPlayers())
            }
        }

        @Nested
        inner class AddPlayers {

            @Test
            fun `adding a Player to a populated list adds to ArrayList`() {
                val newPlayer = Player(0, "2000-01-01", "Haaland", 9, 400000.0, "Striker", false)

                assertEquals(3, populatedPlayers!!.numberOfPlayers())
                populatedPlayers!!.addPlayer(newPlayer)
                assertEquals(4, populatedPlayers!!.numberOfPlayers())
                assertEquals(newPlayer, populatedPlayers!!.findPlayer(3))
            }

            @Test
            fun `adding a Player to an empty list adds to ArrayList`() {
                val newPlayer = Player(0, "2000-01-01", "Haaland", 9, 400000.0, "Striker", false)

                assertEquals(0, emptyPlayers!!.numberOfPlayers())
                emptyPlayers!!.addPlayer(newPlayer)
                assertEquals(1, emptyPlayers!!.numberOfPlayers())
                assertEquals(newPlayer, emptyPlayers!!.findPlayer(0))
            }
        }

        @Nested
        inner class ListPlayers {

            @Test
            fun `listPlayers returns empty list when no players stored`() {
                assertEquals(0, emptyPlayers!!.numberOfPlayers())
                assertTrue(emptyPlayers!!.listPlayers().isEmpty())
            }

            @Test
            fun `listPlayers returns all stored players`() {
                assertEquals(3, populatedPlayers!!.numberOfPlayers())

                val players = populatedPlayers!!.listPlayers()
                assertTrue(players.contains(ronaldo))
                assertTrue(players.contains(messi))
                assertTrue(players.contains(mbappe))
            }
        }
    }

