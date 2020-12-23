import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MultiLineCalculatorTest {

    @Test
    fun getShortestWord() {
        assertEquals("word", MultiLineCalculator().pass(listOf("word", "longerword")).getShortestWord())
        assertEquals("uncasedword", MultiLineCalculator().pass(listOf("CasedWord", "uncasedword")).getShortestWord())
    }

    @Test
    fun getLongestWord() {
        assertEquals("longerword", MultiLineCalculator().pass(listOf("word", "longerword")).getLongestWord())
        assertEquals("CasedWord", MultiLineCalculator().pass(listOf("CasedWord", "uncasedword")).getLongestWord())
    }
}