package ie.setu.controllers


import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ie.setu.models.Player
import org.junit.jupiter.api.Assertions.*
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

            @Nested
            inner class DeletePlayers {

                @Test
                fun `deleting a Player that does not exist returns null`() {
                    assertNull(emptyPlayers!!.deletePlayer(0))
                    assertNull(populatedPlayers!!.deletePlayer(-1))
                    assertNull(populatedPlayers!!.deletePlayer(5))
                }

                @Test
                fun `deleting a Player that exists deletes and returns deleted object`() {
                    assertEquals(3, populatedPlayers!!.numberOfPlayers())

                    assertEquals(mbappe, populatedPlayers!!.deletePlayer(2))
                    assertEquals(2, populatedPlayers!!.numberOfPlayers())

                    assertEquals(ronaldo, populatedPlayers!!.deletePlayer(0))
                    assertEquals(1, populatedPlayers!!.numberOfPlayers())
                }
            }

            @Nested
            inner class UpdatePlayers {

                @Test
                fun `updating a player that does not exist returns false`() {
                    assertFalse(populatedPlayers!!.updatePlayer(99, Player(99, "X", "2000", 0, 0.0, "None", false)))
                    assertFalse(emptyPlayers!!.updatePlayer(0, Player(0, "X", "2000", 0, 0.0, "None", false)))
                }

                @Test
                fun `updating an existing player returns true and updates`() {
                    assertEquals(mbappe, populatedPlayers!!.findPlayer(2))
                    assertEquals("Mbappe", populatedPlayers!!.findPlayer(2)!!.name)
                    assertEquals("25-12-1998", populatedPlayers!!.findPlayer(2)!!.dateOfBirth)
                    assertEquals(25, populatedPlayers!!.findPlayer(2)!!.shirtNo)
                    assertEquals(700000.0, populatedPlayers!!.findPlayer(2)!!.salary)
                    assertEquals("Forward", populatedPlayers!!.findPlayer(2)!!.preferredPlayPosition)


                    assertTrue(populatedPlayers!!.updatePlayer(2, Player(0, "2000-06-01", "Updated Player", 10, 200000.0, "Winger", false)
                        )
                    )

                    assertEquals("Updated Player", populatedPlayers!!.findPlayer(2)!!.name)
                    assertEquals("2000-06-01", populatedPlayers!!.findPlayer(2)!!.dateOfBirth)
                    assertEquals(10, populatedPlayers!!.findPlayer(2)!!.shirtNo)
                    assertEquals(200000.0, populatedPlayers!!.findPlayer(2)!!.salary)
                    assertEquals("Winger", populatedPlayers!!.findPlayer(2)!!.preferredPlayPosition)
                }
            }



        }
    }

