import KeyboardPaneSwitcher.Const.lowSwitch
import KeyboardPaneSwitcher.Const.symbolSwitch
import KeyboardPaneSwitcher.Const.upSwitch
import java.lang.IllegalArgumentException

class CharacterDistanceCalculator {
    internal var lowerChars = FiveRowPane(
        lowSwitch,
        listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
        listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
        listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', '-'),
        listOf('⇧', '⇧', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '\''),
        listOf('⁉', '⁉', 'ȁ', 'ȁ', '␣', '␣', '␣', '⇐', '⇐', '⇐')
    )

    private var upperChars = FiveRowPane(
        upSwitch,
        listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
        listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', '-'),
        listOf('⇩', '⇩', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '\''),
        listOf('⁉', '⁉', 'ȁ', 'ȁ', '␣', '␣', '␣', '⇐', '⇐', '⇐') // TODO support space character
    )

    private var symbols = FiveRowPane(
        symbolSwitch,
        listOf('`', '~', '!', '@', '#', '$', '%', '^', '&', '*'),
        listOf('(', ')', '-', '_', '=', '+', '[', ']', '{', '}'),
        listOf('\\', '|', ';', ':', '\'', '"', ',', '.', '<', '>'),
        listOf('/', '?', '¿', '¡', 'ª', '°', '¢', '€', '£', '¥'),
        listOf('⇩', '⇩', 'ȁ', 'ȁ', '␣', '␣', '␣', '⇐', '⇐', '⇐')
    )

    private val lowerCharsCalculator = OnPaneDistanceCalculator(lowerChars)
    private val upperCharsCalculator = OnPaneDistanceCalculator(upperChars)
    private val symbolsCalculator = OnPaneDistanceCalculator(symbols)
    internal val calculators = listOf(lowerCharsCalculator, upperCharsCalculator, symbolsCalculator)

    fun sumUpDistance(string: String): Int {
        val switcher = KeyboardPaneSwitcher(calculators.map { it.pane })
        val switchString = switcher.insertSwitchCharacters(string)
        val explodedString = switcher.explode(switchString)
        val wordList = explodedString.joinToString("").split(switcher.splitChar)

        // TODO For Netflix the first character is actually "g", any other first char will have a higher initial value
        return wordList.fold(0) { s, word -> s + sumUpSubword(word)}
    }

    private fun sumUpSubword(subword: String): Int {
        val noSwitchChar = subword.elementAtOrElse(1) { subword.first() }
        val calculator = calculators.first { it.pane.contains(noSwitchChar) }

        if (!subword.all { calculator.pane.contains(it) })
            throw IllegalArgumentException("Pane does not contain all chars of $subword")

        return calculator.sumUpDistance(subword)
    }


}