package daniilalexandrov.grep

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

internal class GrepUtilTest {

    private val output = ByteArrayOutputStream()
    private val separator = System.lineSeparator()
    private val pathSeparator = File.separator

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
        Main.main(arrayOf("kotlin", "Tests${pathSeparator}Test.txt"))
        assertEquals("kotlin$separator", output.toString())
    }
    @Test
    fun rParameter() {
        Main.main(arrayOf("-r", "kotlin", "Tests${pathSeparator}Test.txt"))
        assertEquals("kotlin$separator", output.toString())
    }
    @Test
    fun vParameter() {
        Main.main(arrayOf("-v", "kotlin", "Tests${pathSeparator}Test.txt"))
        assertEquals("Kotlin KOtlin${separator}KOTLIN ${separator}QQotlin${separator}KotlinExemplar${separator}",
                output.toString())
    }
    @Test
    fun iParameter() {
        Main.main(arrayOf("-i", "kotlin", "Tests${pathSeparator}Test.txt"))
        assertEquals("Kotlin KOtlin${separator}kotlin${separator}KOTLIN ${separator}KotlinExemplar${separator}",
                output.toString())
    }
    @Test
    fun ivParameters() {
        Main.main(arrayOf("-v", "-i", "ot", "Tests${pathSeparator}Test.txt"))
        assertEquals("", output.toString())
    }
    @Test
    fun rvParameters() {
        Main.main(arrayOf("-v", "-r", "kotlin", "Tests${pathSeparator}Test.txt"))
        assertEquals("Kotlin KOtlin${separator}KOTLIN ${separator}QQotlin${separator}KotlinExemplar$separator",
                output.toString())
    }
}