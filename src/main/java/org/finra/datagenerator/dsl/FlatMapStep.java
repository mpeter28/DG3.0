package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.Map;

public class FlatMapStep implements GenerationStep {

    private FlatMapFunction<Map<String, String>, Map<String, String>> flatMapFunction;

    public FlatMapStep(FlatMapFunction<Map<String, String>, Map<String, String>> flatMapFunction) {
        this.flatMapFunction = flatMapFunction;
    }

    @Override
    public JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork) {
        return previousWork.flatMap(flatMapFunction);
    }
}
