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
    fun `single line word distance`() {
        assertEquals(0, calculator.sumUpDistance("qq"))
        assertEquals(1, calculator.sumUpDistance("qw"))
        assertEquals(9, calculator.sumUpDistance("qp"))
        assertEquals(2, calculator.sumUpDistance("qwq"))
    }

    @Test
    fun `two line word distance`() {
        assertEquals(1, calculator.sumUpDistance("qa"))
        assertEquals(1, calculator.sumUpDistance("aq"))
        assertEquals(2, calculator.sumUpDistance("pl"))
        assertEquals(2, calculator.sumUpDistance("lp"))
    }

    @Test
    fun `three line word distance`() {
        assertEquals(2, calculator.sumUpDistance("qaq"))
        assertEquals(5, calculator.sumUpDistance("qsx"))
        assertEquals(5, calculator.sumUpDistance("zaw"))
        assertEquals(3, calculator.sumUpDistance("plm"))
    }

    @Test
    fun `sample word distance`() {
        assertEquals(11, calculator.sumUpDistance("trump"))
        assertEquals(17, calculator.sumUpDistance("biden"))
    }

    @Test
    fun `two pane distance`() {
        assertEquals(2, calculator.sumUpDistance("aA"))
        assertEquals(4, calculator.sumUpDistance("aAa"))
        assertEquals(3, calculator.sumUpDistance("Aa"))

        assertEquals(3, calculator.sumUpDistance("a/"))
        assertEquals(4, calculator.sumUpDistance("a\\"))
        assertEquals(19, calculator.sumUpDistance("\$a"))

    }

}