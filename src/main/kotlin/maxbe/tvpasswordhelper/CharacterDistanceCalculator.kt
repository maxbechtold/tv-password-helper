package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.lowSwitch
import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.symbolSwitch
import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.umlautLowSwitch
import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.umlautUpSwitch
import maxbe.tvpasswordhelper.KeyboardPaneSwitcher.Switches.upSwitch
import java.lang.IllegalArgumentException

class CharacterDistanceCalculator {
    // TODO: Extract for different services
    // TODO Switch and space characters are over estimated
    // TODO: Also support qwertz layout?
    // Redflix Android TV Keyboard 22/08
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
        listOf('/', '?', '¿', '¡', 'ª', '°', '¢', '€', '£', '¥'),
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

    private val calculators =
        listOf(lowerChars, upperChars, symbols, lowerUmlauts, upperUmlauts).map(::OnPaneDistanceCalculator)

    fun sumUpDistance(string: String): Int {
        val switcher = KeyboardPaneSwitcher(calculators.map { it.pane })
        val switchString = switcher.insertSwitchCharacters(string)
        val explodedString = switcher.explode(switchString)
        val wordList = explodedString.joinToString("").split(switcher.splitChar)

        // TODO For Netflix the initially selected character is "g", so the distance (0-4?) to the first char should be considered
        return wordList.fold(0) { s, word -> s + sumUpSubword(word) }
    }

    private fun sumUpSubword(subword: String): Int {
        val noSwitchChar = subword.elementAtOrElse(1) { subword.first() }
        val calculator = calculators.first { it.pane.contains(noSwitchChar) }

        if (!subword.all { calculator.pane.contains(it) })
            throw IllegalArgumentException("Pane does not contain all chars of $subword")

        return calculator.sumUpDistance(subword)
    }


}