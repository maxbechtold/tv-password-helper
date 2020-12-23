import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ThreeRowPaneTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `test contains`() {
        val pane = CharacterDistanceCalculator.ThreeRowPane('*', listOf('a', 'm'), listOf('b'), listOf('*', 'x'))
        assertTrue(pane.contains('a'))
        assertTrue(pane.contains('x'))
        assertFalse(pane.contains('z'))
    }

    @Test
    fun `must contain switchChar`() {
        assertThrows(IllegalArgumentException::class.java, fun() {
            CharacterDistanceCalculator.ThreeRowPane('!', listOf('a'), listOf('b'), listOf('c'))
        })
    }
}