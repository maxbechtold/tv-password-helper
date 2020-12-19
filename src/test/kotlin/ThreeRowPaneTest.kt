import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ThreeRowPaneTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `test contains`() {
        val pane = CharacterDistanceCalculator.ThreeRowPane(listOf('a', 'm'), listOf('b'), listOf('c', 'x'))
        assertTrue(pane.contains('a'))
        assertTrue(pane.contains('x'))
        assertFalse(pane.contains('z'))
    }
}