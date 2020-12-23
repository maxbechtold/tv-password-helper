fun main(args: Array<String>) {
    val lines = generateSequence(::readLine).takeWhile { it.isNotEmpty() }

    val calculator = MultiLineCalculator()
    val words = lines.toList()
    calculator.pass(words)

    val (word, distance) = calculator.getShortest()

    println("Got ${words.size} words with distance up to ${calculator.getLongest().second}")
    println("Selecting $word ($distance)")
}