import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CharacterDistanceCalculatorTest {

    private lateinit var calculator: CharacterDistanceCalculator

    @BeforeEach
    fun setUp() {
        calculator = CharacterDistanceCalculator()
    }

    @Test
    fun `line 1 distance`() {
        assertEquals(0, calculator.getDirectDistance("q", "q"))
        assertEquals(1, calculator.getDirectDistance("q", "w"))
        assertEquals(2, calculator.getDirectDistance("q", "e"))

        assertEquals(9, calculator.getDirectDistance("q", "p"))
    }

    @Test
    fun `line 2 distance`() {
        assertEquals(0, calculator.getDirectDistance("l", "l"))
        assertEquals(1, calculator.getDirectDistance("k", "l"))

        assertEquals(7, calculator.getDirectDistance("s", "l"))
        assertEquals(8, calculator.getDirectDistance("a", "l"))
    }

    @Test
    fun `line 3 distance`() {
        assertEquals(0, calculator.getDirectDistance("y", "y"))
        assertEquals(1, calculator.getDirectDistance("y", "x"))

        assertEquals(1, calculator.getDirectDistance("n", "m"))
        assertEquals(6, calculator.getDirectDistance("y", "m"))
    }

    @Test
    fun `single line word distance`() {
        assertEquals(0, calculator.sumUpDistance("qq"))
        assertEquals(1, calculator.sumUpDistance("qw"))
        assertEquals(9, calculator.sumUpDistance("qp"))
        assertEquals(2, calculator.sumUpDistance("qwq"))
    }
}