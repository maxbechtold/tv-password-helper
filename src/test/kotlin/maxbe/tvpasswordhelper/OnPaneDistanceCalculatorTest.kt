package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.KeyboardPaneSwitcher
import maxbe.tvpasswordhelper.OnPaneDistanceCalculator
import maxbe.tvpasswordhelper.ThreeRowPane
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OnPaneDistanceCalculatorTest {
    private lateinit var calculator: OnPaneDistanceCalculator

    @BeforeEach
    internal fun setUp() {
        calculator = OnPaneDistanceCalculator(
            ThreeRowPane(
                KeyboardPaneSwitcher.upSwitch,
                listOf('q', 'w', 'e', 'r', 't', 'z', 'u', 'i', 'o', 'p'),
                listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
                listOf('⇧', 'y', 'x', 'c', 'v', 'b', 'n', 'm')
            )
        )
    }

    @Test
    fun `empty word`() {
        assertEquals(0, calculator.sumUpDistance(""))
    }

    @Test
    fun `line 1 distance`() {
        assertEquals(0, calculator.getDistance('q', 'q'))
        assertEquals(1, calculator.getDistance('q', 'w'))
        assertEquals(2, calculator.getDistance('q', 'e'))

        assertEquals(9, calculator.getDistance('q', 'p'))
    }

    @Test
    fun `line 2 distance`() {
        assertEquals(0, calculator.getDistance('l', 'l'))
        assertEquals(1, calculator.getDistance('k', 'l'))

        assertEquals(7, calculator.getDistance('s', 'l'))
        assertEquals(8, calculator.getDistance('a', 'l'))
    }

    @Test
    fun `line 3 distance`() {
        assertEquals(0, calculator.getDistance('y', 'y'))
        assertEquals(1, calculator.getDistance('y', 'x'))

        assertEquals(1, calculator.getDistance('n', 'm'))
        assertEquals(6, calculator.getDistance('y', 'm'))
    }
}