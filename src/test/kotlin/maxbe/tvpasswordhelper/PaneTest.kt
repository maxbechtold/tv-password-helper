package maxbe.tvpasswordhelper

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PaneTest {

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