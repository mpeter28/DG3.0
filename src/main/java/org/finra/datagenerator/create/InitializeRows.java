package org.finra.datagenerator.create;

import org.apache.spark.api.java.function.Function;

import java.util.HashMap;
import java.util.Map;

public class InitializeRows implements Function<Integer, Map<String, String>> {

    @Override
    public Map<String, String> call(Integer integer) throws Exception {
        return new HashMap<>();
    }
}
