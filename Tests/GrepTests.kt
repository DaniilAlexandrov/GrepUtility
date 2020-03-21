package daniilalexandrov.grep

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

internal class GrepUtilTest {

    private val output = ByteArrayOutputStream()
    private val separator = System.lineSeparator()

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
        main(arrayOf("kotlin", "Tests\\Test.txt"))
        assertEquals("kotlin$separator", output.toString())
    }
    @Test
    fun rParameter() {
        main(arrayOf("-r", "kotlin", "Tests\\Test.txt"))
        assertEquals("kotlin$separator", output.toString())
    }
    @Test
    fun vParameter() {
        main(arrayOf("-v", "kotlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin${separator}KOTLIN ${separator}QQotlin\r\nKotlinExemplar${separator}",
                output.toString())
    }
    @Test
    fun iParameter() {
        main(arrayOf("-i", "kotlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin${separator}kotlin${separator}KOTLIN ${separator}KotlinExemplar${separator}",
                output.toString())
    }
    /*@Test
    fun irParameters() {
        main(arrayOf("-r", "-i", "qqotlin", "Tests\\Test.txt"))
        assertEquals("QQotlin$separator", output.toString())
    }
    В этом месте кода у меня есть три варианта, что делать. Хотелось бы узнать, какой наиболее предпочтителен.
    1) Убрать этот тест.
    2) Тестировать недопустимый случай через AssertThrows.
    3) Вынести тест ошибки в string переменную в начале файла и сравнивать через AssertEquals вывод с текстом ошибки.
    */
    @Test
    fun ivParameters() {
        main(arrayOf("-v", "-i", "ot", "Tests\\Test.txt"))
        assertEquals("", output.toString())
    }
    @Test
    fun rvParameters() {
        main(arrayOf("-v", "-r", "kotlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin${separator}KOTLIN ${separator}QQotlin${separator}KotlinExemplar$separator",
                output.toString())
    }
    /*@Test
    fun rivParameters() {
        main(arrayOf("-i","-v", "-r", "[\\w+]otlin", "Tests\\Test.txt"))
        assertEquals("Kotlin KOtlin${separator}KOTLIN ${separator}QQotlin${separator}KotlinExemplar$separator",
                output.toString())
    }*/
}