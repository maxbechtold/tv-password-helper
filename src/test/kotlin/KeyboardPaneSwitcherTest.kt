import KeyboardPaneSwitcher.Const.lowSwitch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import KeyboardPaneSwitcher.Const.upSwitch

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
        assertEquals("q${upSwitch}Q", switcher.insertSwitchCharacters("qQ"))
        assertEquals("b${upSwitch}AV", switcher.insertSwitchCharacters("bAV"))
        assertEquals("h${upSwitch}TT${lowSwitch}p", switcher.insertSwitchCharacters("hTTp"))
    }

    @Test
    fun `start with upper case`() {
        assertEquals("${upSwitch}O${lowSwitch}h", switcher.insertSwitchCharacters("Oh"))
        assertEquals("${upSwitch}H${lowSwitch}mm", switcher.insertSwitchCharacters("Hmm"))
        assertEquals("${upSwitch}G${lowSwitch}mb${upSwitch}H", switcher.insertSwitchCharacters("GmbH"))
    }
}