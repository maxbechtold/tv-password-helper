class KeyboardPaneSwitcher(private val panes: Collection<IPane<Char>>) {

    companion object Const {
        const val upSwitch = '⇧'
        const val lowSwitch = '⇩'
        const val symbolSwitch = '⁉'
        const val umlautSwitch = 'ȁ'
    }

    internal val switchCharacters = "$upSwitch$lowSwitch$symbolSwitch$umlautSwitch"
    internal val switchBackCharacters = "$lowSwitch$upSwitch$lowSwitch$lowSwitch"
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
            else -> panes.first { it.contains(next) }.switchChar.toString() + next
        }
    }

    internal fun explode(switchString: String) =
        switchString.flatMap { if (switchCharacters.contains(it)) switchBack(it) else listOf(it) }


}