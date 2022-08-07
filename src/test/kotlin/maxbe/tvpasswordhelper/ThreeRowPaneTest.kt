package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.ThreeRowPane
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ThreeRowPaneTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `test contains`() {
        val pane = ThreeRowPane('*', listOf('a', 'm'), listOf('b'), listOf('*', 'x'))
        assertTrue(pane.contains('a'))
        assertTrue(pane.contains('x'))
        assertFalse(pane.contains('z'))
    }

}