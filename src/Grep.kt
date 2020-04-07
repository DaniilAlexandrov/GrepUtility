package daniilalexandrov.grep

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.regex.Pattern

class Grep(_file: File, _regex: Boolean, _invert: Boolean, _ignore: Boolean, _count: Boolean, _word: String) {
    private val file: File = _file

    private val regex = _regex

    private val invert = _invert

    private val ignore = _ignore

    private val count = _count

    private val word: String = _word

     private fun flagDeterminant(line: String): Boolean {
        val res = if (!regex) {
            if (!ignore)
                line.contains(word)
            else
                line.toUpperCase().contains(word.toUpperCase())
        } else {
            val pattern = Pattern.compile(word)
            pattern.matcher(line).find()
        }
        return invert != res
    }
fun formResult() = if (!count)
            BufferedReader(FileReader(file)).use { it1 -> it1.readLines() }.
                    filter { flagDeterminant(it) }.forEach { println(it) }
else
            println(BufferedReader(FileReader(file)).use { it1 -> it1.readLines() }.
                    filter { flagDeterminant(it) }.count())
}