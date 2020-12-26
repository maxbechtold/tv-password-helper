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
    fun `space words`() {
        assertEquals(4, calculator.sumUpDistance("b c"))
        assertEquals(6, calculator.sumUpDistance("b b"))
        assertEquals(3, calculator.sumUpDistance("x c"))
        assertEquals(4, calculator.sumUpDistance("x x"))
        assertEquals(10, calculator.sumUpDistance("x X"))
        assertEquals(13, calculator.sumUpDistance("X x"))
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
    fun `ambiguous char line distance`() {
        assertEquals(2, calculator.sumUpDistance("1a"))
        assertEquals(2, calculator.sumUpDistance("a1"))
        assertEquals(4, calculator.sumUpDistance("1aA"))
        assertEquals(3, calculator.sumUpDistance("A1"))
        assertEquals(4, calculator.sumUpDistance("1A"))
        assertEquals(4, calculator.sumUpDistance("a1a"))
        assertEquals(6, calculator.sumUpDistance("a1A"))
        assertEquals(7, calculator.sumUpDistance("A1a"))
        assertEquals(5, calculator.sumUpDistance("A1A"))
    }

    @Test
    fun `dual step pane switch`() {
        assertEquals(16, calculator.sumUpDistance("°C"))
        assertEquals(11, calculator.sumUpDistance("A+"))
        assertEquals(12, calculator.sumUpDistance("1€"))
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


    @Test
    fun `sample word distance`() {
        assertEquals(11, calculator.sumUpDistance("trump"))
        assertEquals(17, calculator.sumUpDistance("biden"))
    }

    @Test
    fun `password distance`() {
        assertEquals(77, calculator.sumUpDistance("WtZ1IfJBqsTR"))
        assertEquals(77, calculator.sumUpDistance("KPBURKehopL1"))
        assertEquals(81, calculator.sumUpDistance("mmvPyDffIL0 "))

        assertEquals(96, calculator.sumUpDistance("g464Oo2dMh2_"))
        assertEquals(74, calculator.sumUpDistance("6wvGqY-jtxCF"))
        assertEquals(81, calculator.sumUpDistance("bSL6Y-_fSR6u"))

        assertEquals(123, calculator.sumUpDistance("XOn>N1VPvQ(X"))
        assertEquals(101, calculator.sumUpDistance("}-]_8{)DwgGH"))
        assertEquals(96, calculator.sumUpDistance("7BR])LZn58GH"))
    }
}