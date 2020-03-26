package daniilalexandrov.grep

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.File

class Logic {
    @Option(name = "-r", metaVar = "Regex", usage = "Sets a regular expression", forbids = ["-i"])
    private var regex = false

    @Option(name = "-v", metaVar = "Invert", usage = "Reverts regex condition")
    private var invert = false

    @Option(name = "-i", metaVar = "Ignore", usage = "Ignores passed text's case")
    private var ignore = false

    @Argument(required = true, metaVar = "Word", usage = "Regex word requirement")
    private lateinit var word: String

    @Argument(required = true, metaVar = "Document", index = 1, usage = "Document to filter")
    private lateinit var file: File

    fun passArguments(args: Array<String>) {
        val argsParser = CmdLineParser(this)
        try {
            argsParser.parseArgument(*args)
        } catch (e: CmdLineException) {
            println(e.message)
            println("Required format is: java -jar GrepUtility.jar -v -i/-r  Word input_name.txt")
            argsParser.printUsage(System.err)
            return
        }
        Grep(file, regex, invert, ignore, word).findSuitingLines().forEach { println(it) }
    }
}