package org.finra.datagenerator.format;

import org.apache.spark.api.java.function.Function;

import java.util.Map;

public class DelimiterFormat implements Function<Map<String, String>, String> {

    private char delimiter;
    private String[] outputColumnOrder;

    public DelimiterFormat(char delimiter, String[] outputColumnOrder) {
        this.delimiter = delimiter;
        this.outputColumnOrder = outputColumnOrder;
    }

    @Override
    public String call(Map<String, String> stringStringMap) throws Exception {
        StringBuilder buildResults = new StringBuilder();
        for (String outputColumn : outputColumnOrder) {
            buildResults.append(stringStringMap.get(outputColumn));
            buildResults.append(delimiter);
        }

        return buildResults.toString();
    }
}
