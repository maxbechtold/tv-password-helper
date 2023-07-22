package maxbe.tvpasswordhelper.service

import maxbe.tvpasswordhelper.FiveRowPane
import maxbe.tvpasswordhelper.OnPaneDistanceCalculator

// Redflix Android TV Keyboard 22/08
class NetflixQwerty : Keyboard {
    override val inputComfort = InputComfort(holdForCaps = false, horizontalWrapAround = true, initialSelection = 'g')

    override fun getCalculators() =
        listOf(lowerChars, upperChars, symbols, lowerUmlauts, upperUmlauts).map(::OnPaneDistanceCalculator)
    override fun isSwitch(char: Char) = switchCharacters.contains(char)

    override fun backwardSwitch(switch: Char): Char {
        require(isSwitch(switch))
        return switchBackCharacters[switchCharacters.indexOf(switch)]
    }


    // TODO Switch and space characters are over estimated
    // TODO: Also support qwertz layout?

    companion object {
        private const val lowSwitch = 'ā'
        private const val upSwitch = 'Ā'
        private const val symbolSwitch = '⁉'
        private const val umlautLowSwitch = 'ȁ'

        private const val umlautUpSwitch = 'Ȁ'
        internal const val switchCharacters = "$upSwitch$lowSwitch$symbolSwitch$umlautLowSwitch$umlautUpSwitch"
        internal const val switchBackCharacters = "$lowSwitch$upSwitch$lowSwitch$lowSwitch$upSwitch"

        init {
            require(switchCharacters.length == switchBackCharacters.length)
        }

        private var lowerChars = FiveRowPane(
            lowSwitch,
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
            listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'),
            listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', '-'),
            listOf('Ā', 'Ā', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '\''),
            listOf('⁉', '⁉', 'ȁ', 'ȁ', ' ', ' ', ' ', '⇐', '⇐', '⇐')
        )

        private var upperChars = FiveRowPane(
            upSwitch,
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
            listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
            listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', '-'),
            listOf('ā', 'ā', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '\''),
            listOf('⁉', '⁉', 'ȁ', 'ȁ', ' ', ' ', ' ', '⇐', '⇐', '⇐')
        )

        private var symbols = FiveRowPane(
            symbolSwitch,
            listOf('`', '~', '!', '@', '#', '$', '%', '^', '&', '*'),
            listOf('(', ')', '-', '_', '=', '+', '[', ']', '{', '}'),
            listOf('\\', '|', ';', ':', '\'', '"', ',', '.', '<', '>'),
            listOf('/', '?', '¡', '¿', 'ª', '°', '¢', '€', '£', '¥'),
            listOf('ā', 'ā', 'ȁ', 'ȁ', ' ', ' ', ' ', '⇐', '⇐', '⇐')
        )

        private var lowerUmlauts = FiveRowPane(
            umlautLowSwitch,
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
            listOf('à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é'),
            listOf('ê', 'ë', 'ì', 'í', 'î', 'ï', 'ñ', 'ò', 'ó', 'ô'),
            listOf('Ȁ', 'õ', 'ö', 'ø', 'œ', 'ß', 'ù', 'ú', 'û', 'ü'),
            listOf('⁉', '⁉', 'ā', 'ā', ' ', ' ', ' ', '⇐', '⇐', '⇐')
        )

        private var upperUmlauts = FiveRowPane(
            umlautUpSwitch,
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'),
            listOf('À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É'),
            listOf('Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ñ', 'Ò', 'Ó', 'Ô'),
            listOf('ȁ', 'Õ', 'Ö', 'Ø', 'Œ', 'ß', 'Ù', 'Ú', 'Û', 'Ü'),
            listOf('⁉', '⁉', 'Ā', 'Ā', ' ', ' ', ' ', '⇐', '⇐', '⇐')
        )

    }

}