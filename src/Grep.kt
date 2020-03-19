package daniilalexandrov.grep

import java.util.regex.Pattern
import org.kohsuke.args4j.Option
import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class Grep {
    companion object {
    @JvmStatic
    fun main(args: Array<String>) {
        Grep().passArguments(args)
    }
}
    @Option(name = "-r", metaVar = "Regex", usage = "Sets a regular expression")
    private var regex = false

    @Option(name = "-v", metaVar = "Invert", usage = "Reverts regex condition")
    private var invert = false

    @Option(name = "-i", metaVar = "Ignore", usage = "Ignores passed text's case")
    private var ignore = false

    @Argument(required = true, metaVar = "Word", usage = "Regex word requirement")
    private var word: String? = null

    @Argument(required = true, metaVar = "Document", index = 1, usage = "Document to filter")
    private var file: File? = null

private fun flagDeterminant(line: String): Boolean {
    val res = if (!regex) {
        if (!ignore)
            line.contains(word!!)
        else
        line.toUpperCase().contains(word!!.toUpperCase())
        } else {
        val pattern = Pattern.compile(word!!)
        val matcher = pattern.matcher(line)
        if (!ignore)
            matcher.matches()
        else
            matcher.matches() || pattern.matcher(line.toUpperCase()).matches() ||
                    pattern.matcher(line.toLowerCase()).matches()
    }
    return invert != res
}

private fun passArguments(args: Array<String>) {
    val argsParser = CmdLineParser(this)
    try {
        argsParser.parseArgument(*args)
    } catch (e: CmdLineException) {
        println(e.message)
        println("Required format is: java -jar GrepUtility.jar -v Invert -i Ignore_Register -r Regex word input_name.txt")
        argsParser.printUsage(System.err)
        return
    }
    val matchingLines = BufferedReader(FileReader(file!!)).use { it1 -> it1.readLines().filter { flagDeterminant(it) } }
    for (line in matchingLines)
        println(line)
}
}