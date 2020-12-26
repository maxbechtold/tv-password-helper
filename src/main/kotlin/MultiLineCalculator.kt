class MultiLineCalculator {

    private lateinit var inputsSorted: List<Pair<String, Int>>

    fun pass(lines: List<String>): MultiLineCalculator {
        require(lines.isNotEmpty())

        val calculator = CharacterDistanceCalculator()
        inputsSorted = lines
            .mapIndexed { index, s -> index to calculator.sumUpDistance(s) }
            .sortedWith { o1, o2 -> o1.second - o2.second }
            .map { lines[it.first] to it.second }
            .map { it.first.mapIndexed { index, c -> if (index < 2) c else '.' }.joinToString("") to it.second }
        return this
    }

    fun getChosen() = inputsSorted.first()

    fun getChosenWord() = getChosen().first

    fun getHardest() = inputsSorted.last()

    fun getHardestWord() = getHardest().first
}