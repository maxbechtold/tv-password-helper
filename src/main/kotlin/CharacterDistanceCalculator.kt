import kotlin.math.abs

class CharacterDistanceCalculator {
    private var lowerChars = ThreeRowPane(
        listOf("q", "w", "e", "r", "t", "z", "u", "i", "o", "p"),
        listOf("a", "s", "d", "f", "g", "h", "j", "k", "l"),
        listOf("⇧", "y", "x", "c", "v", "b", "n", "m"))

    data class ThreeRowPane<T>(val row1: List<T>, val row2: List<T>, val row3: List<T>) {
        fun getRows() = listOf(row1, row2, row3)
        fun getRow(id: Int) = getRows()[id]
        fun findRow(char: String) = getRows().indexOfFirst { row -> row.contains(char) }
    }

    fun sumUpDistance(string: String): Int {
        val chars = string.map { it.toString() }.toList()
        val charPairs = chars.zipWithNext()

        return charPairs.fold(0) { sum, pair -> sum + getDistance(pair.first, pair.second) }
    }

    fun getDistance(char1: String, char2: String): Int {
        val rowId1 = lowerChars.findRow(char1)
        val rowId2 = lowerChars.findRow(char2)
        val index1 = lowerChars.getRow(rowId1).indexOf(char1)
        val index2 = lowerChars.getRow(rowId2).indexOf(char2)

        return abs(rowId1 - rowId2) + abs(index1 - index2)
    }

}