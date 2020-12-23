import KeyboardPaneSwitcher.Const.lowSwitch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import KeyboardPaneSwitcher.Const.upSwitch

internal class KeyboardPaneSwitcherTest {

    private lateinit var switcher: KeyboardPaneSwitcher

    private var lowerChars = ThreeRowPane(
        lowSwitch,
        listOf('q', 'w', 'e', 'r', 't', 'z', 'u', 'i', 'o', 'p'),
        listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        listOf('⇧', 'y', 'x', 'c', 'v', 'b', 'n', 'm')
    )

    private var upperChars = ThreeRowPane(
        upSwitch,
        listOf('Q', 'W', 'E', 'R', 'T', 'Z', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
        listOf('⇩', 'Y', 'X', 'C', 'V', 'B', 'N', 'M')
    )


    @BeforeEach
    fun setUp() {
        switcher = KeyboardPaneSwitcher(listOf(lowerChars, upperChars))
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

    @Test
    fun `switch chars have back char`() {
        assertEquals(switcher.switchCharacters.length, switcher.switchBackCharacters.length)
    }
}