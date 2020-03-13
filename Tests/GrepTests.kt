import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class GrepUtilTest {

    private val output = ByteArrayOutputStream()

    @BeforeEach
    fun startStream() {
        System.setOut(PrintStream(output))
    }

    @AfterEach
    fun clearStream() {
        System.setOut(null)
    }

    @Test
    fun noParameters() {
        Grep.main(arrayOf("kotlin", "Tests\\Test.txt"))
        assertEquals("kotlin\r\n", output.toString())
    }
    @Test
    fun rParameter() {
        Grep.main(arrayOf("-r", "kotlin", "Tests\\Test.txt"))
        assertEquals("kotlin\r\n", output.toString())
    }
    @Test
    fun vParameter() {
        Grep.main(arrayOf("-v", "kotlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin\r\nKOTLIN \r\nQQotlin\r\nKotlinExemplar\r\n", output.toString())
    }
    @Test
    fun iParameter() {
        Grep.main(arrayOf("-i", "kotlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin\r\nkotlin\r\nKOTLIN \r\nKotlinExemplar\r\n", output.toString())
    }
    @Test
    fun irParameters() {
        Grep.main(arrayOf("-r", "-i", "qqotlin", "Tests\\Test.txt"))
        assertEquals("QQotlin\r\n", output.toString())
    }
    @Test
    fun ivParameters() {
        Grep.main(arrayOf("-v", "-i", "ot", "Tests\\Test.txt"))
        assertEquals("", output.toString())
    }
    @Test
    fun rvParameters() {
        Grep.main(arrayOf("-v", "-r", "kotlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin\r\nKOTLIN \r\nQQotlin\r\nKotlinExemplar\r\n", output.toString())
    }
    @Test
    fun rivParameters() {
        Grep.main(arrayOf("-i","-v", "-r", "[\\w+]otlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin\r\nKOTLIN \r\nQQotlin\r\nKotlinExemplar\r\n", output.toString())
    }
}