package maxbe.tvpasswordhelper

fun main() {
    // TODO Test this
    println("Enter words line by line. Calculation starts after first empty line")
    val readFunction: () -> String? = { readLine() }
    val lines = generateSequence(readFunction).takeWhile { it.isNotEmpty() }

    val words = lines.toList()
    val calculator = MultiLineCalculator().pass(words)

    val (word, distance) = calculator.getChosen()

    println("Got ${words.size} words with distance up to ${calculator.getHardest().second}")
    println("Proposing $word ($distance)")
}