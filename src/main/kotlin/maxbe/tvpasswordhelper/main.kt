package maxbe.tvpasswordhelper

import maxbe.tvpasswordhelper.service.Service
import superpat.Menu
import superpat.Option

fun main() {
    val service = getServiceChoice()

    // TODO Test this
    println("Enter words line by line. Calculation starts after first empty line")
    val readFunction: () -> String? = { readlnOrNull() }
    val lines = generateSequence(readFunction).takeWhile { it.isNotEmpty() }

    val words = lines.toList()
    val calculator = MultiLineCalculator(service).pass(words)

    val (word, distance) = calculator.getChosen()

    println("Got ${words.size} words with distance up to ${calculator.getHardest().second}")
    println("Proposing $word ($distance)")

    val exitDelaySeconds = 5
    println()
    println("Exiting in $exitDelaySeconds seconds...")
    Thread.sleep(exitDelaySeconds * 1000L)
}

private fun getServiceChoice(): Service {
    val serviceMenu = Menu(
        "Which service are you interested in?",
        listOf(
            Option("Netflix", Service.Netflix),
            Option("Disney+", Service.DisneyPlus),
            Option("Paramount+", Service.ParamountPlus)
        )
    )
    return serviceMenu.spawnMenu()
}