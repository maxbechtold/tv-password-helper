import KeyboardPaneSwitcher.Const.lowSwitch
import KeyboardPaneSwitcher.Const.upSwitch

class CharacterDistanceCalculator {
    private var lowerChars = ThreeRowPane(upSwitch,
        listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
        listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        listOf('⇧', 'z', 'x', 'c', 'v', 'b', 'n', 'm'))

    private var upperChars = ThreeRowPane(lowSwitch,
        listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
        listOf('⇩', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'))

    data class ThreeRowPane<T>(val switchChar: Char, val row1: List<T>, val row2: List<T>, val row3: List<T>) : IPane<T> {
        init {
            require(contains(switchChar)) { "Must contain $switchChar" }
        }

        override fun getRows() = listOf(row1, row2, row3)
        override fun getRow(id: Int) = getRows()[id]
        override fun findRow(char: Char) = getRows().indexOfFirst { it.contains(char) }
        override fun contains(char: Char) = findRow(char) != -1
    }

    private val lowerCharsCalculator = OnPaneDistanceCalculator(lowerChars)
    private val upperCharsCalculator = OnPaneDistanceCalculator(upperChars)

    fun sumUpDistance(string: String): Int {
        val switcher = KeyboardPaneSwitcher(listOf(lowerChars, upperChars))
        val switchString = switcher.insertSwitchCharacters(string)
        val explodedString = switcher.explode(switchString)
        val wordList = explodedString.joinToString("").split(switcher.splitChar)

        // TODO For Netflix the first character is actually "g", any other first char will have a higher initial value
        return wordList.fold(0) { s, word -> s + sumUpSubword(word)}
    }

    private fun sumUpSubword(subword: String): Int {
        val calculator = listOf(lowerCharsCalculator, upperCharsCalculator).first { it.pane.contains(subword.first()) }

        return calculator.sumUpDistance(subword)
    }


}