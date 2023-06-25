package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.service.DisneyPlus
import maxbe.tvpasswordhelper.service.Netflix
import java.util.logging.Logger

class CharacterDistanceCalculator {

    val LOG = Logger.getLogger(this.javaClass.name)

    // TODO: Make service a parameter/program argument
    val calculators = Netflix.calculators  // DisneyPlus
    fun sumUpDistance(string: String): Int {
        val switcher = KeyboardPaneSwitcher(calculators.map { it.pane })
        val switchString = switcher.insertSwitchCharacters(string)
        val explodedString = switcher.explode(switchString)
        LOG.info("Exploded string: $explodedString")
        val wordList = explodedString.joinToString("").split(switcher.splitChar)

        // TODO Distance (0-4?) to the first char should be considered (cf. InputComfort)
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