import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import KeyboardPaneSwitcher.Const.caseSwitch

internal class KeyboardPaneSwitcherTest {

    private lateinit var switcher: KeyboardPaneSwitcher

    @BeforeEach
    fun setUp() {
        switcher = KeyboardPaneSwitcher()
    }

    @Test
    fun `no switch`() {
        assertEquals("qay", switcher.insertSwitchCharacters("qay"))
    }

    @Test
    fun `start with lower case`() {
        assertEquals("q${caseSwitch}Q", switcher.insertSwitchCharacters("qQ"))
        assertEquals("b${caseSwitch}AV", switcher.insertSwitchCharacters("bAV"))
        assertEquals("h${caseSwitch}TT${caseSwitch}p", switcher.insertSwitchCharacters("hTTp"))
    }

    @Test
    fun `start with upper case`() {
        assertEquals("${caseSwitch}O${caseSwitch}h", switcher.insertSwitchCharacters("Oh"))
        assertEquals("${caseSwitch}H${caseSwitch}mm", switcher.insertSwitchCharacters("Hmm"))
        assertEquals("${caseSwitch}G${caseSwitch}mb${caseSwitch}H", switcher.insertSwitchCharacters("GmbH"))
    }
}