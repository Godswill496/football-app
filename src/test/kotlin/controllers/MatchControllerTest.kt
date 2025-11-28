package ie.setu.controllers

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ie.setu.models.Match
import ie.setu.persistence.JSONSerializer
import ie.setu.persistence.XMLSerializer
import org.junit.jupiter.api.Assertions.*
import java.io.File

class MatchControllerTest {

    private var match1: Match? = null
    private var match2: Match? = null
    private var match3: Match? = null

    private var populatedMatches: MatchController? = MatchController(XMLSerializer(File("match.xml")))
    private var emptyMatches: MatchController? = MatchController(XMLSerializer(File("empty-match.xml")))

    @BeforeEach
    fun setup() {
        match1 = Match(0, "El Clasico", "Camp Nou", "Xavi")
        match2 = Match(0, "Derby", "Bernabeu", "Ancelotti")
        match3 = Match(0, "Champions League", "Parc des Princes", "Enrique")

        populatedMatches!!.addMatch(match1!!)
        populatedMatches!!.addMatch(match2!!)
        populatedMatches!!.addMatch(match3!!)
    }

    @AfterEach
    fun tearDown() {
        match1 = null
        match2 = null
        match3 = null
        populatedMatches = null
        emptyMatches = null
    }

    @Nested
    inner class AddMatches {

        @Test
        fun `adding a match to a populated list adds correctly`() {
            val newMatch = Match(0, "Final", "Wembley", "Southgate")

            assertEquals(3, populatedMatches!!.numberOfMatches())
            populatedMatches!!.addMatch(newMatch)
            assertEquals(4, populatedMatches!!.numberOfMatches())
            assertEquals(newMatch, populatedMatches!!.findMatch(3))
        }

        @Test
        fun `adding a match to an empty list adds correctly`() {
            val newMatch = Match(0, "Final", "Wembley", "Southgate")

            assertEquals(0, emptyMatches!!.numberOfMatches())
            emptyMatches!!.addMatch(newMatch)
            assertEquals(1, emptyMatches!!.numberOfMatches())
            assertEquals(newMatch, emptyMatches!!.findMatch(0))
        }
    }

    @Nested
    inner class ListMatches {

        @Test
        fun `list matches with no matches returns empty list`() {
            assertEquals(0, emptyMatches!!.numberOfMatches())
            assertTrue(emptyMatches!!.listMatches().isEmpty())
        }

        @Test
        fun `list matches returns all stored matches`() {
            assertEquals(3, populatedMatches!!.numberOfMatches())
            val list = populatedMatches!!.listMatches()
            assertTrue(list.contains(match1))
            assertTrue(list.contains(match2))
            assertTrue(list.contains(match3))
        }

        @Nested
        inner class DeleteMatches {

            @Test
            fun `deleting a Match that does not exist returns null`() {
                assertNull(emptyMatches!!.deleteMatch(0))
                assertNull(populatedMatches!!.deleteMatch(-1))
                assertNull(populatedMatches!!.deleteMatch(5))
            }

            @Test
            fun `deleting a Match that exists deletes and returns deleted object`() {
                assertEquals(3, populatedMatches!!.numberOfMatches())

                assertEquals(match3, populatedMatches!!.deleteMatch(2))
                assertEquals(2, populatedMatches!!.numberOfMatches())
            }
        }
        @Nested
        inner class MatchPersistenceTests {

            @Test
            fun `saving and loading an empty match collection in XML doesn't crash app`() {
                val storingMatches = MatchController(XMLSerializer(File("matches.xml")))
                storingMatches.store()

                val loadedMatches = MatchController(XMLSerializer(File("matches.xml")))
                loadedMatches.load()

                assertEquals(0, storingMatches.numberOfMatches())
                assertEquals(0, loadedMatches.numberOfMatches())
                assertEquals(storingMatches.numberOfMatches(), loadedMatches.numberOfMatches())
            }

            @Test
            fun `saving and loading a populated match collection in XML doesn't lose data`() {
                val storingMatches = MatchController(XMLSerializer(File("matches.xml")))

                val m1 = Match(0, "Barcelona vs Madrid", "Camp Nou", "Ancelotti")
                val m2 = Match(0, "PSG vs Bayern", "Parc des Princes", "Luis Enrique")
                val m3 = Match(0, "Chelsea vs Arsenal", "Stamford Bridge", "Arteta")

                storingMatches.addMatch(m1)
                storingMatches.addMatch(m2)
                storingMatches.addMatch(m3)
                storingMatches.store()

                val loadedMatches = MatchController(XMLSerializer(File("matches.xml")))
                loadedMatches.load()

                assertEquals(3, storingMatches.numberOfMatches())
                assertEquals(3, loadedMatches.numberOfMatches())
                assertEquals(storingMatches.findMatch(0), loadedMatches.findMatch(0))
                assertEquals(storingMatches.findMatch(1), loadedMatches.findMatch(1))
                assertEquals(storingMatches.findMatch(2), loadedMatches.findMatch(2))
            }
        }

        @Nested
        inner class JsonPersistenceTests {

            @Test
            fun `saving and loading an empty match list doesn't crash`() {
                val storing = MatchController(JSONSerializer(File("matches.json")))
                storing.store()

                val loaded = MatchController(JSONSerializer(File("matches.json")))
                loaded.load()

                assertEquals(0, storing.numberOfMatches())
                assertEquals(0, loaded.numberOfMatches())
            }

            @Test
            fun `saving and loading a populated match list keeps data`() {
                val storing = MatchController(JSONSerializer(File("matches.json")))
                storing.addMatch(match1!!)
                storing.addMatch(match2!!)
                storing.addMatch(match3!!)
                storing.store()

                val loaded = MatchController(JSONSerializer(File("matches.json")))
                loaded.load()

                assertEquals(3, loaded.numberOfMatches())
                assertEquals(storing.findMatch(0), loaded.findMatch(0))
                assertEquals(storing.findMatch(1), loaded.findMatch(1))
                assertEquals(storing.findMatch(2), loaded.findMatch(2))
            }
        }


    }
}
