package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.service.Netflix

class MultiLineCalculator {

    private lateinit var inputsSorted: List<Pair<String, Int>>

    fun pass(lines: List<String>): MultiLineCalculator {
        require(lines.isNotEmpty()) { "Must specify one or more words" }

        // TODO Will always pick those of lowest complexity too (e.g. alphanumeric over those with one or more symbols)
        val calculator = CharacterDistanceCalculator(Netflix.calculators)
        val wordsSorted = lines
            .mapIndexed { index, s -> index to calculator.sumUpDistance(s) }
            .sortedWith { o1, o2 -> o1.second - o2.second }
        inputsSorted = wordsSorted
            .map { lines[it.first] to it.second }
            .map { it.first.mapIndexed { index, c -> if (index < 2) c else '.' }.joinToString("") to it.second }
        return this
    }

    private val hardnessThreshold = 0.6

    fun getChosen() = inputsSorted.first {it.second >= hardnessThreshold * getHardest().second}

    fun getChosenWord() = getChosen().first

    fun getHardest() = inputsSorted.last()

    fun getHardestWord() = getHardest().first
}