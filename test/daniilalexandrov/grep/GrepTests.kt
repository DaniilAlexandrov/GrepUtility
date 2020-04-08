package daniilalexandrov.grep

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.file.Paths

internal class GrepTest {

    private val output = ByteArrayOutputStream()
    private val separator = System.lineSeparator()
    private val testFolderDestination = Paths.get("test","resources", "Test.txt").toString()
    private val defaultStreamState = System.out

    @BeforeEach
    fun startStream() {
        System.setOut(PrintStream(output))
    }
    @AfterEach
    fun setStreamToDefault() {
        System.setOut(defaultStreamState)
    }

    @Test
    fun noParameters() {
        main(arrayOf("kotlin", testFolderDestination))
        assertEquals("kotlin$separator", output.toString())
    }
    @Test
    fun rParameter() {
        main(arrayOf("-r", "kotlin", testFolderDestination))
        assertEquals("kotlin$separator", output.toString())
    }
    @Test
    fun vParameter() {
        main(arrayOf("-v", "kotlin", testFolderDestination))
        assertEquals("Kotlin KOtlin${separator}KOTLIN ${separator}QQotlin${separator}KotlinExemplar${separator}",
                output.toString())
    }
    @Test
    fun iParameter() {
        main(arrayOf("-i", "kotlin", testFolderDestination))
        assertEquals("Kotlin KOtlin${separator}kotlin${separator}KOTLIN ${separator}KotlinExemplar${separator}",
                output.toString())
    }
    @Test
    fun cParameter() {
        main(arrayOf("-c", "kotlin", testFolderDestination))
        assertEquals("1$separator", output.toString())
    }
    @Test
    fun ivParameters() {
        main(arrayOf("-v", "-i", "ot", testFolderDestination))
        assertEquals("", output.toString())
    }
    @Test
    fun civParameters() {
        main(arrayOf("-c", "-v", "-i", "ot", testFolderDestination))
        assertEquals("0$separator", output.toString())
    }
    @Test
    fun rvParameters() {
        main(arrayOf("-v", "-r", "kotlin", testFolderDestination))
        assertEquals("Kotlin KOtlin${separator}KOTLIN ${separator}QQotlin${separator}KotlinExemplar$separator",
                output.toString())
    }
}