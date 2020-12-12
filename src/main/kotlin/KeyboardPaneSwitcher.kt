import java.lang.IllegalArgumentException

class KeyboardPaneSwitcher {

    enum class Mode {
        LowerChar,
        UpperChar
    }

    companion object Const {
        const val caseSwitch = "⇧"
    }

    fun insertSwitchCharacters(word: String) : String {
        return word.map { it }.fold("") {sequence, char -> sequence + mapToSequence(char, getMode(sequence))}
    }

    private fun getMode(sequence: String): Mode {
        return when {
            sequence.isEmpty() -> Mode.LowerChar
            sequence.last().isUpperCase() -> Mode.UpperChar
            sequence.last().isLowerCase() -> Mode.LowerChar
            else -> throw IllegalArgumentException(sequence)
        }
    }

    private fun mapToSequence(it: Char, mode: Mode) : String {
        if (it.isLowerCase() && mode == Mode.LowerChar) {
            return it.toString()
        } else if (it.isUpperCase() && mode == Mode.UpperChar) {
            return it.toString()
        }

        return caseSwitch+it
    }
}