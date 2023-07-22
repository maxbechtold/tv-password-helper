package superpat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream
import kotlin.test.assertEquals
import java.lang.System.lineSeparator as SEP


class MenuTest {

    @Test
    fun menuMustNotHaveEmptyOptionList() {
        assertThrows<Exception> {
            Menu("my menu", emptyList<Option<String>>()).spawnMenu(InputStream.nullInputStream())
        }
    }

    @Test
    fun menuWithSingleOption() {
        val menu = Menu("What's your choice?", listOf(Option("one", "one")))

        val answer = "1"
        val inputStream: InputStream = ByteArrayInputStream(answer.toByteArray())
        val outputStream = ByteArrayOutputStream()
        val choice = menu.spawnMenu(inputStream, PrintStream(outputStream))

        assertEquals("What's your choice?${SEP()}[1] one${SEP()}", outputStream.toString())
        assertEquals("one", choice)
    }

    @Test
    fun menuWithMultipleOptionsAndInputMistake() {
        val menu = Menu(
            "real menu",
            listOf(Option("One", 1), Option("Two", 2), Option("Three", 3))
        )

        val answer = "0${SEP()}2"
        val inputStream: InputStream = ByteArrayInputStream(answer.toByteArray())
        val outputStream = ByteArrayOutputStream()
        val choice = menu.spawnMenu(inputStream, PrintStream(outputStream))

        assertEquals(2, choice)
    }

}
