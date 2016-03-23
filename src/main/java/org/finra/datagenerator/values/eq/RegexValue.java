package org.finra.datagenerator.values.eq;

import org.finra.datagenerator.values.ValueOracle;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValue implements ValueOracle {

    private Pattern regexPattern;

    public RegexValue(String regexPattern) {
        this.regexPattern = Pattern.compile(regexPattern);
    }

    public String generateValue(long seed) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random(seed);
        Matcher matcher = regexPattern.matcher("");

        int[] cypher = new int[95];
        boolean done = false;

        //start from an empty string and grow a solution
        while (!done) {
            //make a cypher to jumble the order letters are tried
            for (int i = 0; i < 95; i++) {
                cypher[i] = i;
            }

            for (int i = 0; i < 95; i++) {
                int n = random.nextInt(95 - i) + i;

                int t = cypher[n];
                cypher[n] = cypher[i];
                cypher[i] = t;
            }

            //try and grow partial solution using an extra letter on the end
            for (int i = 0; i < 95; i++) {
                int n = cypher[i] + 32;
                builder.append((char) n);

                String result = builder.toString();
                matcher.reset(result);
                if (matcher.matches()) { //complete solution found
                    //don't try to expand to a larger solution
                    if (!random.nextBoolean()) {
                        done = true;
                    }

                    break;
                } else if (matcher.hitEnd()) { //prefix to a solution found, keep this letter
                    break;
                } else { //dead end found, try a new character at the end
                    builder.deleteCharAt(builder.length() - 1);

                    //no more possible characters to try and expand with - stop
                    if (i == 94) {
                        done = true;
                    }
                }
            }
        }

        return builder.toString();
    }
}
