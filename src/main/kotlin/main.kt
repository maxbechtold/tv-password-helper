fun main(args: Array<String>) {
    // TODO Test this
    println("Enter words line by line. Calculation starts after first empty line")
    val readFunction: () -> String? = { System.console()?.readPassword()?.toString() ?: readLine() }
    val lines = generateSequence(readFunction).takeWhile { it.isNotEmpty() }

    val words = lines.toList()
    val calculator = MultiLineCalculator().pass(words)

    val (word, distance) = calculator.getChosen()

    println("Got ${words.size} words with distance up to ${calculator.getHardest().second}")
    println("Proposing $word ($distance)")
}