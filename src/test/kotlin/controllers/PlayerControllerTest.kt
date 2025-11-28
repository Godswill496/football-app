package ie.setu.controllers

import ie.setu.models.Player
import ie.setu.persistence.JSONSerializer
import ie.setu.persistence.XMLSerializer
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.io.File
import kotlin.test.assertFalse

class PlayerControllerTest {

    private var ronaldo: Player? = null
    private var messi: Player? = null
    private var mbappe: Player? = null

    private var populatedPlayers: PlayerController? =
        PlayerController(XMLSerializer(File("players.xml")))

    private var emptyPlayers: PlayerController? =
        PlayerController(XMLSerializer(File("empty-players.xml")))

    @BeforeEach
    fun setup() {
        ronaldo = Player(0, "1985-02-05", "Ronaldo", 7, 500000.0, "Striker", false)
        messi   = Player(0, "1987-06-24", "Messi", 10, 600000.0, "Forward", true)
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
        fun `listFitPlayers returns only players who are not injured`() {
            val result = populatedPlayers!!.listFitPlayers()

            assertTrue(result.contains("Ronaldo"))
            assertTrue(result.contains("Mbappe"))
            assertFalse(result.contains("Messi")) // Messi is injured
        }

        @Test
        fun `numberOfFitPlayers counts correct non injured players`() {
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
        fun `numberOfInjuredPlayers counts correct injured players`() {
            assertEquals(1, populatedPlayers!!.numberOfInjuredPlayers())
            assertEquals(0, emptyPlayers!!.numberOfInjuredPlayers())
        }
    }


    @Nested
    inner class AddPlayers {

        @Test
        fun `adding a Player to a populated list increases count`() {
            val newPlayer = Player(0, "2000-01-01", "Haaland", 9, 400000.0, "Striker", false)

            assertEquals(3, populatedPlayers!!.numberOfPlayers())
            populatedPlayers!!.addPlayer(newPlayer)
            assertEquals(4, populatedPlayers!!.numberOfPlayers())
            assertEquals(newPlayer, populatedPlayers!!.findPlayer(3))
        }

        @Test
        fun `adding a Player to an empty list stores successfully`() {
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
        fun `listPlayers returns empty when no players stored`() {
            assertEquals(0, emptyPlayers!!.numberOfPlayers())
            assertTrue(emptyPlayers!!.listPlayers().isEmpty())
        }

        @Test
        fun `listPlayers returns all players when populated`() {
            assertEquals(3, populatedPlayers!!.numberOfPlayers())
            val players = populatedPlayers!!.listPlayers()

            assertTrue(players.contains(ronaldo))
            assertTrue(players.contains(messi))
            assertTrue(players.contains(mbappe))
        }


        @Nested
        inner class DeletePlayers {

            @Test
            fun `deleting invalid index returns null`() {
                assertNull(populatedPlayers!!.deletePlayer(-1))
                assertNull(populatedPlayers!!.deletePlayer(10))
            }

            @Test
            fun `deleting valid index removes correct player`() {
                assertEquals(3, populatedPlayers!!.numberOfPlayers())

                val removed = populatedPlayers!!.deletePlayer(1)
                assertEquals(messi, removed)
                assertEquals(2, populatedPlayers!!.numberOfPlayers())
            }
        }


        @Nested
        inner class UpdatePlayers {

            @Test
            fun `updating non existing player returns false`() {
                assertFalse(
                    populatedPlayers!!.updatePlayer(
                        20, Player(0, "X", "Name", 0, 0.0, "None", false)
                    )
                )
            }

            @Test
            fun `updating existing player changes data`() {

                val update = Player(
                    0,
                    "2000-06-01",
                    "Updated Player",
                    10,
                    200000.0,
                    "Winger",
                    false
                )

                assertTrue(populatedPlayers!!.updatePlayer(2, update))

                val updated = populatedPlayers!!.findPlayer(2)!!
                assertEquals("Updated Player", updated.name)
                assertEquals("2000-06-01", updated.dateOfBirth)
                assertEquals(10, updated.shirtNo)
                assertEquals(200000.0, updated.salary)
                assertEquals("Winger", updated.preferredPlayPosition)
            }
        }


        @Nested
        inner class XmlPersistenceTests {

            @Test
            fun `saving and loading empty player list works`() {
                val storing = PlayerController(XMLSerializer(File("players.xml")))
                storing.store()

                val loaded = PlayerController(XMLSerializer(File("players.xml")))
                loaded.load()

                assertEquals(0, loaded.numberOfPlayers())
            }

            @Test
            fun `saving and loading populated list retains data`() {
                val storing = PlayerController(XMLSerializer(File("players.xml")))

                val p1 = Player(0, "1990-01-01", "Ronaldo", 7, 500000.0, "Striker", false)
                val p2 = Player(0, "1987-06-24", "Messi", 10, 600000.0, "Forward", false)

                storing.addPlayer(p1)
                storing.addPlayer(p2)
                storing.store()

                val loaded = PlayerController(XMLSerializer(File("players.xml")))
                loaded.load()

                assertEquals(2, loaded.numberOfPlayers())
                assertEquals(p1, loaded.findPlayer(0))
                assertEquals(p2, loaded.findPlayer(1))
            }
        }


        @Nested
        inner class JsonPersistenceTests {

            @Test
            fun `saving and loading empty JSON list works`() {
                val storing = PlayerController(JSONSerializer(File("players.json")))
                storing.store()

                val loaded = PlayerController(JSONSerializer(File("players.json")))
                loaded.load()

                assertEquals(0, loaded.numberOfPlayers())
            }

            @Test
            fun `saving and loading populated JSON list keeps data`() {
                val storing = PlayerController(JSONSerializer(File("players.json")))

                storing.addPlayer(Player(0,"1990-01-01","Test1",7,30000.0,"Midfielder",false))
                storing.addPlayer(Player(0,"1988-04-02","Test2",10,40000.0,"Striker",true))
                storing.store()

                val loaded = PlayerController(JSONSerializer(File("players.json")))
                loaded.load()

                assertEquals(2, loaded.numberOfPlayers())
                assertEquals(storing.findPlayer(0), loaded.findPlayer(0))
                assertEquals(storing.findPlayer(1), loaded.findPlayer(1))
            }


            @Nested
            inner class ArchivePlayers {

                @Test
                fun `archiving a fit player marks them injured and returns true`() {

                    assertFalse(populatedPlayers!!.findPlayer(0)!!.isInjured)

                    val result = populatedPlayers!!.archivePlayer(0)

                    assertTrue(result)
                    assertTrue(populatedPlayers!!.findPlayer(0)!!.isInjured)
                }

                @Test
                fun `archiving an already injured player returns false`() {

                    populatedPlayers!!.archivePlayer(1)


                    val result = populatedPlayers!!.archivePlayer(1)

                    assertFalse(result)
                }

                @Test
                fun `archiving using invalid index returns false`() {
                    assertFalse(populatedPlayers!!.archivePlayer(-1))
                    assertFalse(populatedPlayers!!.archivePlayer(99))
                    assertFalse(emptyPlayers!!.archivePlayer(0))
                }
            }

        }
    }
}


