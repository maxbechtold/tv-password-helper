package maxbe.tvpasswordhelper

class CharacterDistanceCalculator(private val calculators: List<OnPaneDistanceCalculator>) {

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