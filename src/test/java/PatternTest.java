import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PatternTest
 * Create by cy on 2018/5/10
 **/
public class PatternTest {

    protected static List<Pattern> patterns = new ArrayList<Pattern>();


    private static boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("login.*html");
        patterns.add(pattern);
        pattern = Pattern.compile("/.*/.*");
        patterns.add(pattern);
        System.out.println(PatternTest.isInclude("/login"));

    }
}
