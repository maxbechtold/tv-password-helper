import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CharacterDistanceCalculatorTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `line distance`() {
        val calculator = CharacterDistanceCalculator()
        assertEquals(0, calculator.getDirectDistance("q", "q"))
        assertEquals(1, calculator.getDirectDistance("q", "w"))
        assertEquals(2, calculator.getDirectDistance("q", "e"))

        assertEquals(9, calculator.getDirectDistance("q", "p"))
    }
}