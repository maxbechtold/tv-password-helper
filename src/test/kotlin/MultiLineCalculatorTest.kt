import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

internal class MultiLineCalculatorTest {

    @Test
    fun getChosenWord() {
        assertEquals("wo..", MultiLineCalculator().pass(listOf("word", "longerword")).getChosenWord())
        assertEquals("un.........", MultiLineCalculator().pass(listOf("CasedWord", "uncasedword")).getChosenWord())
    }

    @Test
    fun noEmptyList() {
        assertThrows(IllegalArgumentException::class.java) {MultiLineCalculator().pass(emptyList()) }
    }

    @Test
    fun getHardestWord() {
        assertEquals("lo........", MultiLineCalculator().pass(listOf("word", "longerword")).getHardestWord())
        assertEquals("Ca.......", MultiLineCalculator().pass(listOf("CasedWord", "uncasedword")).getHardestWord())
    }
}