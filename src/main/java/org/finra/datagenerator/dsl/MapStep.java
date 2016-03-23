package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.util.Map;

public class MapStep implements GenerationStep {

    private Function<Map<String, String>, Map<String, String>> mapFunction;

    public MapStep(Function<Map<String, String>, Map<String, String>> mapFunction) {
        this.mapFunction = mapFunction;
    }

    @Override
    public JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork) {
        return previousWork.map(mapFunction);
    }
}
