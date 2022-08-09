package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.lowSwitch
import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.umlautLowSwitch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.upSwitch
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertContains

internal class KeyboardPaneSwitcherTest {

    companion object {
        const val UPS = upSwitch
        const val LOS = lowSwitch
        const val UML = umlautLowSwitch
    }

    private lateinit var switcher: KeyboardPaneSwitcher

    private var lowerChars = ThreeRowPane(
        lowSwitch,
        listOf('q', 'w', 'e', 'r', 't', 'z', 'u', 'i', 'o', 'p'),
        listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        listOf(UPS, 'y', 'x', 'c', 'v', 'b', 'n', 'm')
    )

    private var upperChars = ThreeRowPane(
        upSwitch,
        listOf('Q', 'W', 'E', 'R', 'T', 'Z', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
        listOf(LOS, 'Y', 'X', 'C', 'V', 'B', 'N', UML)
    )


    private var umlautChars = ThreeRowPane(
        umlautLowSwitch,
        listOf('à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é'),
        listOf('ê', 'ë', 'ì', 'í', 'î', 'ï', 'ñ', 'ò', 'ó', 'ô'),
        listOf(UPS, 'õ', 'ö', 'ø', 'œ', 'ß', 'ù', 'ú', 'û', LOS),
    )


    @BeforeEach
    fun setUp() {
        switcher = KeyboardPaneSwitcher(listOf(lowerChars, upperChars, umlautChars))
    }

    @Test
    fun `no switch`() {
        assertEquals("qay", switcher.insertSwitchCharacters("qay"))
    }

    @Test
    fun `throws for unsupported characters`() {
        val exception = assertThrows<IllegalArgumentException> { switcher.insertSwitchCharacters("T€") }
        assertContains(exception.message.toString(), "not supported: €")
    }

    @Test
    fun `start with lower case`() {
        assertEquals("q${UPS}Q", switcher.insertSwitchCharacters("qQ"))
        assertEquals("b${UPS}AV", switcher.insertSwitchCharacters("bAV"))
        assertEquals("h${UPS}TT${LOS}p", switcher.insertSwitchCharacters("hTTp"))
    }

    @Test
    fun `start with upper case`() {
        assertEquals("${UPS}O${LOS}h", switcher.insertSwitchCharacters("Oh"))
        assertEquals("${UPS}H${LOS}mm", switcher.insertSwitchCharacters("Hmm"))
        assertEquals("${UPS}G${LOS}mb${UPS}H", switcher.insertSwitchCharacters("GmbH"))
    }

    @Test
    fun `start with umlaut`() {
        assertEquals("${UPS}${UML}ß", switcher.insertSwitchCharacters("ß"))
        assertEquals("${UPS}${UML}ä${LOS}h", switcher.insertSwitchCharacters("äh"))
        assertEquals("${UPS}${UML}ø${UPS}G", switcher.insertSwitchCharacters("øG"))
    }

    @Test
    fun `switch chars have different back char`() {
        assertEquals(switcher.switchCharacters.length, switcher.switchBackCharacters.length)
        assertFalse(switcher.switchCharacters.zip(switcher.switchBackCharacters) { a, b -> a == b }.any { it })
    }

}