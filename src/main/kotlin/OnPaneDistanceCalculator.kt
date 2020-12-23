import kotlin.math.abs

class OnPaneDistanceCalculator(val pane: IPane<Char>) {

    // TODO For broad keys (e.g. space) this will calculate too high a distance
    private fun calculateDistance(char1: Char, char2: Char): Int {
        val rowId1 = pane.findRow(char1)
        val rowId2 = pane.findRow(char2)
        val index1 = pane.getRow(rowId1).indexOf(char1)
        val index2 = pane.getRow(rowId2).indexOf(char2)

        return abs(rowId1 - rowId2) + abs(index1 - index2)
    }

    fun sumUpDistance(string: String): Int {
        val chars = string.map { it }.toList()
        val charPairs = chars.zipWithNext()

        return charPairs.fold(0) { sum, pair -> sum + getDistance(pair.first, pair.second) }
    }


    fun getDistance(char1: Char, char2: Char): Int {
        return calculateDistance(char1, char2)
    }

}
