package org.finra.datagenerator.assignment;

import org.apache.spark.api.java.function.Function;
import org.finra.datagenerator.values.ValuePool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomAssign implements Function<Map<String, String>, Map<String, String>> {

    private String variableName;
    private ValuePool values;

    public RandomAssign(String variableName, ValuePool values) {
        this.variableName = variableName;
        this.values = values;
    }

    @Override
    public Map<String, String> call(Map<String, String> stringStringMap) {
        List<String> options = values.values(0);
        int choiceIndex = (int) (Math.random() * options.size()) + 1;
        String chosenValue = options.get(choiceIndex);

        HashMap<String, String> updatedMap = new HashMap<>(stringStringMap);
        updatedMap.put(variableName, chosenValue);
        return updatedMap;
    }
}
