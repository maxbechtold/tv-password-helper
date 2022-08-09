package maxbe.tvpasswordhelper

import java.lang.IllegalArgumentException

class KeyboardPaneSwitcher(private val panes: Collection<IPane<Char>>) {

    companion object Switches {
        const val lowSwitch = 'ā'
        const val upSwitch = 'Ā'
        const val symbolSwitch = '⁉'
        const val umlautLowSwitch = 'ȁ'
        const val umlautUpSwitch = 'Ȁ'
    }

    internal val switchCharacters = "$upSwitch$lowSwitch$symbolSwitch$umlautLowSwitch$umlautUpSwitch"
    internal val switchBackCharacters = "$lowSwitch$upSwitch$lowSwitch$lowSwitch$upSwitch"
    val splitChar = '☒'

    private fun switchBack(char: Char): Iterable<Char> {
        return listOf(char, splitChar, backwards(char))
    }

    private fun backwards(char: Char) = switchBackCharacters[switchCharacters.indexOf(char)]

    fun insertSwitchCharacters(word: String) : String {
        return word.map { it }.fold("") {sequence, char -> sequence + mapToSequence(char, sequence)}
    }

    private fun mapToSequence(next: Char, last: String) : String {
        val lastOrDefault = last.ifEmpty { "anylowercase" }
        val lastSubwordIndex = lastOrDefault.indexOfLast { switchCharacters.contains(it) } + 1
        val wordOnPane = last.substring(lastSubwordIndex)

        return when {
            panes.first { pane -> wordOnPane.all { pane.contains(it) }}.contains(next) -> next.toString()
            else -> mapToSwitchChars(wordOnPane, next) + next
        }
    }

    private fun mapToSwitchChars(word: String, next: Char): String {
        val targetPane = panes.firstOrNull { it.contains(next) }
            ?: throw IllegalArgumentException("Character is not supported: $next")
        val sourcePane = panes.first { pane -> word.all { pane.contains(it) } }

        if (sourcePane.contains(targetPane.switchChar))
            return targetPane.switchChar.toString()

        val viaPane = panes.first { it.contains(targetPane.switchChar)}
        // TODO: Since not all panes are reachable from everywhere, this is a shortest-path problem... solve in a generic way?
        val viaSwitch = viaPane.switchChar
        if (!sourcePane.contains(viaSwitch))
            throw IllegalArgumentException("Cannot switch to pane containing $next via $viaSwitch")

        return viaSwitch + targetPane.switchChar.toString()
    }

    internal fun explode(switchString: String) =
        switchString.flatMap { if (switchCharacters.contains(it)) switchBack(it) else listOf(it) }


}