package superpat

import java.io.InputStream
import java.io.PrintStream
import java.util.*
import java.util.logging.Logger

/** (Adapted to Kotlin from https://gist.github.com/Superpat/619aa7033e416796876e)
 * Menu api for the command line
 *
 *
 * Takes a list of options containing a value and a menu title, when called, the menu returns an optional type containing the option.
 *
 * @author Patrick Marchand <mail></mail>@patrickmarchand.com>
 * @version 0.2
 * @since 2015-11-18
 */
class Menu<T : Any>(private val title: String, private val options: List<Option<T>>) {

    private val logger = Logger.getLogger(this.javaClass.name)

    init {
        require(options.isNotEmpty()) { "Must specify at least one option" }
    }

    /**
     * Prints the menu to standard output and captures the users choice
     * @return The chosen option value
     */
    fun spawnMenu(inputStream: InputStream = System.`in`, outStream: PrintStream = System.out): T {
        val scanner = Scanner(inputStream)
        outStream.println(title)
        do {
            options.indices.forEach { outStream.println("[${(it + 1)}] ${options[it].text}") }
            if (!scanner.hasNextInt()) {
                scanner.next()
                outStream.println("Enter a number.")
                continue
            }
            val choice = scanner.nextInt()
            if (choice > 0 && choice <= options.size ) {
                val option = options[choice - 1]
                logger.fine { "Choice was option ${option.text}" }
                return option.option
            }
            outStream.println("Invalid choice.")
        } while (true)
    }

}