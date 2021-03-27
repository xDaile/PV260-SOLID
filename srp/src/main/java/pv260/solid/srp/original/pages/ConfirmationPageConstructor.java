package pv260.solid.srp.original.pages;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


public class ConfirmationPageConstructor {

    private static final Pattern COST_REGEX = compile("order=([\\w]+)&cost=([\\w]+)");

    public static String constructConfirmationPage(String query) throws Exception {
        try (InputStream in = ConfirmationPageConstructor.class.getResourceAsStream("../confirm.html");
             Scanner scan = new Scanner(in).useDelimiter("\\A")) {
            String template = scan.next();

            Matcher costMatcher = COST_REGEX.matcher(query);
            costMatcher.find();

            return template.replace("{ORDER}",
                    costMatcher.group(1))
                    .replace("{COST}",
                            costMatcher.group(2));
        }
    }
}
