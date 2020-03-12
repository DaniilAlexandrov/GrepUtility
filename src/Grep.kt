import java.util.regex.Matcher
import java.util.regex.Pattern
import org.kohsuke.args4j.Option

class Grep {
    @Option(name = "-r", metaVar = "Regex", usage = "Passes a regular expression")
    private val regex = false


}