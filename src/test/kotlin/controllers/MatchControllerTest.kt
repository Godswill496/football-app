package ie.setu.controllers

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import ie.setu.models.Match

class MatchControllerTest {

    private var match1: Match? = null
    private var match2: Match? = null
    private var match3: Match? = null

    private var populatedMatches: MatchController? = MatchController()
    private var emptyMatches: MatchController? = MatchController()

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
    }
}
