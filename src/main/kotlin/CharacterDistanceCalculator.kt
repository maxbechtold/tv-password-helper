import java.util.regex.Pattern
import kotlin.math.abs

class CharacterDistanceCalculator {

    private val row1 = listOf("q", "w", "e", "r", "t", "z", "u", "i", "o", "p")
    private val row2 = listOf("a", "s", "d", "f", "g", "h", "j", "k", "l")
    private val row3 = listOf("⇧", "y", "x", "c", "v", "b", "n", "m")

    fun getDirectDistance(char1: String, char2: String): Int {
        return when {
            row1.contains(char1) -> row1.getRowDistance(char1, char2)
            row2.contains(char1) -> row2.getRowDistance(char1, char2)
            else -> row3.getRowDistance(char1, char2)
        }

    }

    /** Calculate distance. Returns nonsense for items not in the list */
    private fun List<Any>.getRowDistance(item1: Any, item2: Any): Int = abs(indexOf(item1) - indexOf(item2))

    fun sumUpDistance(string: String): Int {
        val chars = string.map { it.toString() }.toList()
        val charPairs = chars.zipWithNext()

        // q,w  w,e
        return charPairs.fold(0) { sum, pair -> sum + getDirectDistance(pair.first, pair.second) }
    }
}