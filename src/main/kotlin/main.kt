fun main(args: Array<String>) {
    // TODO Test this
    val readFunction: () -> String? = { System.console()?.readPassword()?.toString() ?: readLine() }
    val lines = generateSequence(readFunction).takeWhile { it.isNotEmpty() }

    val words = lines.toList()
    val calculator = MultiLineCalculator().pass(words)

    val (word, distance) = calculator.getShortest()

    println("Got ${words.size} words with distance up to ${calculator.getLongest().second}")
    println("Selecting $word ($distance)")
}