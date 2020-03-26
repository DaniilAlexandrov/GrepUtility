package daniilalexandrov.grep

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.regex.Pattern

class Grep(_file: File, _regex: Boolean, _invert: Boolean, _ignore: Boolean, _word: String) {
    private var file: File = _file

    private var regex = _regex

    private var invert = _invert

    private var ignore = _ignore

    private var word: String = _word

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
fun findSuitingLines() =
        BufferedReader(FileReader(file)).use { it1 -> it1.readLines() }.filter { flagDeterminant(it) }
}