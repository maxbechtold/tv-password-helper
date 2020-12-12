import kotlin.math.abs

class CharacterDistanceCalculator {

    private val topChars = listOf("q", "w", "e", "r", "t", "z", "u", "i", "o", "p");

    fun getDirectDistance(char1: String, char2: String): Int {
        if (char1 == char2) {
            return 0
        }
        return abs(topChars.indexOf(char1) - topChars.indexOf(char2))
    }
}