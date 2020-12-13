import KeyboardPaneSwitcher.Const.caseSwitch
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
        assertEquals(0, calculator.getDistance("q", "q"))
        assertEquals(1, calculator.getDistance("q", "w"))
        assertEquals(2, calculator.getDistance("q", "e"))

        assertEquals(9, calculator.getDistance("q", "p"))
    }

    @Test
    fun `line 2 distance`() {
        assertEquals(0, calculator.getDistance("l", "l"))
        assertEquals(1, calculator.getDistance("k", "l"))

        assertEquals(7, calculator.getDistance("s", "l"))
        assertEquals(8, calculator.getDistance("a", "l"))
    }

    @Test
    fun `line 3 distance`() {
        assertEquals(0, calculator.getDistance("y", "y"))
        assertEquals(1, calculator.getDistance("y", "x"))

        assertEquals(1, calculator.getDistance("n", "m"))
        assertEquals(6, calculator.getDistance("y", "m"))
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

        assertEquals(2, calculator.sumUpDistance("q$caseSwitch"))
        assertEquals(3, calculator.sumUpDistance(caseSwitch +"w"))
    }

    @Test
    fun `three line word distance`() {
        assertEquals(2, calculator.sumUpDistance("qa$caseSwitch"))
        assertEquals(4, calculator.sumUpDistance("qsx"))
        assertEquals(4, calculator.sumUpDistance("yaw"))
        assertEquals(4, calculator.sumUpDistance("plm"))
    }
}