package maxbe.tvpasswordhelper.service

import maxbe.tvpasswordhelper.FiveRowPane
import maxbe.tvpasswordhelper.FourRowPane
import maxbe.tvpasswordhelper.OnPaneDistanceCalculator

// Android TV Keyboard 23/06
class DisneyPlusQwerty : Keyboard {
    // TODO space characters are over estimated
    // TODO: Also support qwertz layout?

    override val inputComfort = InputComfort(holdForCaps = true, horizontalWrapAround = true, initialSelection = 'q')


    override fun getCalculators(): List<OnPaneDistanceCalculator> =
        listOf(lowerChars, upperChars, specialChars, symbols).map(::OnPaneDistanceCalculator)

    override fun isSwitch(char: Char) = switchCharacters.contains(char)

    override fun backwardSwitch(switch: Char): Char {
        require(isSwitch(switch))
        return switchBackCharacters[switchCharacters.indexOf(switch)]
    }

    companion object {

        private const val lowSwitch = 'ā'
        private const val upSwitch = 'Ā'
        private const val specialCharSwitch = '⁉'
        private const val symbolSwitch = '♮'

        private const val switchCharacters = "$upSwitch$lowSwitch$specialCharSwitch$symbolSwitch"
        private const val switchBackCharacters = "$lowSwitch$upSwitch$lowSwitch$specialCharSwitch"

        init {
            require(switchCharacters.length == switchBackCharacters.length)
        }

        private var lowerChars = FiveRowPane(
            lowSwitch,
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
            listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
            listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ','),
            listOf('Ā', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '.', '⇐'),
            listOf('⁉', '◀', '▶', ' ', ' ', ' ', '-', '_', '⏎', '⏎')
        )

        private var upperChars = FiveRowPane(
            upSwitch,
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
            listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
            listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ','),
            listOf('ā', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '.', '⇐'),
            listOf('⁉', '◀', '▶', ' ', ' ', ' ', '-', '_', '⏎', '⏎')
        )

        private var specialChars = FourRowPane(
            specialCharSwitch,
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
            listOf('@', '#', '$', '_', '&', '-', '+', '(', ')', '/'),
            listOf('♮', '*', '"', '\'', ':', ';', '!', '?', '%', '⇐'),
            listOf('ā', '◀', '▶', ' ', ' ', ' ', '.', ',', '⏎', '⏎')
        )

        private var symbols = FourRowPane(
            symbolSwitch,
            listOf('~', '`', '|', '•', '√', 'π', '÷', '×', '¶', 'Δ'),
            listOf('£', '¢', '€', '¥', '^', '°', '=', '{', '}', '\\'),
            listOf('⁉', '%', '©', '®', '✓', '[', ']', '<', '>', '⇐'),
            listOf('ā', '◀', '▶', ' ', ' ', ' ', '.', ',', '⏎', '⏎')
        )

    }


}