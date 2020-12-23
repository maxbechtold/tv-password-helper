class MultiLineCalculator {

    private lateinit var inputsSorted: List<Pair<String, Int>>

    fun pass(lines: List<String>): MultiLineCalculator {
        val calculator = CharacterDistanceCalculator()
        inputsSorted = lines
            .mapIndexed { index, s -> index to calculator.sumUpDistance(s) }
            .sortedWith { o1, o2 -> o1.second - o2.second }
            .map { Pair(lines[it.first], it.second) }
        return this
    }

    fun getShortest() = inputsSorted.first()

    fun getShortestWord() = getShortest().first

    fun getLongest() = inputsSorted.last()

    fun getLongestWord() = getLongest().first
}