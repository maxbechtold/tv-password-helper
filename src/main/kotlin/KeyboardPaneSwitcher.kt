import CharacterDistanceCalculator.ThreeRowPane

class KeyboardPaneSwitcher(private val panes: Collection<ThreeRowPane<Char>>) {

    companion object Const {
        const val upSwitch = '⇧'
        const val lowSwitch = '⇩'
    }

    internal val switchCharacters = "$upSwitch$lowSwitch"
    internal val switchBackCharacters = "$lowSwitch$upSwitch"
    val splitChar = '☒'

    private fun switchBack(char: Char): Iterable<Char> {
        return listOf(char, splitChar, backwards(char))
    }

    private fun backwards(char: Char) = switchBackCharacters[switchCharacters.indexOf(char)]

    fun insertSwitchCharacters(word: String) : String {
        return word.map { it }.fold("") {sequence, char -> sequence + mapToSequence(char, sequence.lastOrNull())}
    }

    private fun mapToSequence(next: Char, last: Char?) : String {
        val lastOrDefault = last ?: "anylowercase".first()
        return when {
            panes.any { it.contains(next) && it.contains(lastOrDefault) } -> next.toString()
            else -> backwards(panes.first { it.contains(next) }.switchChar).toString() + next
        }
    }

    internal fun explode(switchString: String) =
        switchString.flatMap { if (switchCharacters.contains(it)) switchBack(it) else listOf(it) }


}