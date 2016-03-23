package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.util.Map;

public class FilterStep implements GenerationStep {

    private Function<Map<String, String>, Boolean> filter;

    public FilterStep(Function<Map<String, String>, Boolean> filter) {
        this.filter = filter;
    }

    public JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork) {
        return previousWork.filter(filter);
    }
}
