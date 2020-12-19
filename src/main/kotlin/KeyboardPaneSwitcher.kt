import java.lang.IllegalArgumentException

class KeyboardPaneSwitcher {

    enum class Mode {
        LowerChar,
        UpperChar
    }

    companion object Const {
        const val upSwitch = "⇧"
        const val lowSwitch = "⇩"
    }

    fun insertSwitchCharacters(word: String) : String {
        return word.map { it }.fold("") {sequence, char -> sequence + mapToSequence(char, getMode(sequence))}
    }

    private fun getMode(sequence: String): Mode {
        return when {
            sequence.isEmpty() -> Mode.LowerChar
            // TODO Let pane decide
            sequence.last().isUpperCase() || sequence.last().toString() == lowSwitch -> Mode.UpperChar
            sequence.last().isLowerCase() || sequence.last().toString() == upSwitch -> Mode.LowerChar
            else -> throw IllegalArgumentException(sequence)
        }
    }

    private fun mapToSequence(it: Char, mode: Mode) : String {
        if (it.isLowerCase() && mode == Mode.LowerChar) {
            return it.toString()
        } else if (it.isUpperCase() && mode == Mode.UpperChar) {
            return it.toString()
        }

        val switchChar = if (it.isLowerCase()) lowSwitch else upSwitch
        return "$switchChar$it"
    }
}