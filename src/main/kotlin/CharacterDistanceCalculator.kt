import KeyboardPaneSwitcher.Const.lowSwitch
import KeyboardPaneSwitcher.Const.upSwitch
import java.util.regex.Pattern

class CharacterDistanceCalculator {
    private var lowerChars = ThreeRowPane(
        listOf('q', 'w', 'e', 'r', 't', 'z', 'u', 'i', 'o', 'p'),
        listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'),
        listOf('⇧', 'y', 'x', 'c', 'v', 'b', 'n', 'm'))

    private var upperChars = ThreeRowPane(
        listOf('Q', 'W', 'E', 'R', 'T', 'Z', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
        listOf('⇩', 'Y', 'X', 'C', 'V', 'B', 'N', 'M'))

    data class ThreeRowPane<T>(val row1: List<T>, val row2: List<T>, val row3: List<T>) : IPane<T> {
        override fun getRows() = listOf(row1, row2, row3)
        override fun getRow(id: Int) = getRows()[id]
        override fun findRow(char: Char) = getRows().indexOfFirst { it.contains(char) }
        override fun contains(char: Char) = findRow(char) != -1
    }

    private val lowerCharsCalculator = OnPaneDistanceCalculator(lowerChars)
    private val upperCharsCalculator = OnPaneDistanceCalculator(upperChars)

    private val x = "$upSwitch$lowSwitch"
    private val subwordPattern = Pattern.compile("([$x]?.+)([$x])(.*)")

    fun sumUpDistance(string: String): Int {
        val switchString = KeyboardPaneSwitcher().insertSwitchCharacters(string)
        return sumUpRecursive(switchString)
    }

    private fun sumUpRecursive(switchString: String): Int {
        val matcher = subwordPattern.matcher(switchString)
        if (!matcher.find())
            return sumUpSubword(switchString)

        val subword = matcher.group(1)
        val switchChar = matcher.group(2)
        val remainder = matcher.group(3)

        // TODO Generalize
        val switchBackChar = if (switchChar == upSwitch) lowSwitch else upSwitch
        return sumUpSubword(subword, switchChar) + sumUpRecursive(switchBackChar + remainder)
    }

    private fun sumUpSubword(subword: String, switchChar: String? = ""): Int {
        val calculator = listOf(lowerCharsCalculator, upperCharsCalculator).first { it.pane.contains(subword.first()) }

        return calculator.sumUpWord(subword + switchChar)
    }


}