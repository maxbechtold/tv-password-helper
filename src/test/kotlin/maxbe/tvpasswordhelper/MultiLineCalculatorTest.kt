package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.service.Service
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class MultiLineCalculatorTest {

    @Test
    fun getChosenWord() {
        assertEquals("on.....", MultiLineCalculator(Service.Netflix).pass(listOf("oneword", "otherword")).getChosenWord())
        assertEquals("un.........", MultiLineCalculator(Service.Netflix).pass(listOf("CasedWord", "uncasedword")).getChosenWord())
    }

    @Test
    fun noEmptyList() {
        assertThrows(IllegalArgumentException::class.java) { MultiLineCalculator(Service.Netflix).pass(emptyList()) }
    }

    @Test
    fun getHardestWord() {
        assertEquals("lo........", MultiLineCalculator(Service.Netflix).pass(listOf("word", "longerword")).getHardestWord())
        assertEquals("Ca.......", MultiLineCalculator(Service.Netflix).pass(listOf("CasedWord", "uncasedword")).getHardestWord())
    }
}