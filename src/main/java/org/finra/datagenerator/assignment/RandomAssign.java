package org.finra.datagenerator.assignment;

import org.apache.spark.api.java.function.Function;
import org.finra.datagenerator.values.ValuePool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAssign implements Function<Map<String, String>, Map<String, String>> {

    private String variableName;
    private ValuePool values;

    public RandomAssign(String variableName, ValuePool values) {
        this.variableName = variableName;
        this.values = values;
    }

    @Override
    public Map<String, String> call(Map<String, String> row) {
        int seed = row.hashCode();
        List<String> options = values.values(seed);

        Random random = ThreadLocalRandom.current();
        random.setSeed(seed);
        int choiceIndex = random.nextInt(options.size());
        String chosenValue = options.get(choiceIndex);

        HashMap<String, String> derivedRow = new HashMap<>(row);
        derivedRow.put(variableName, chosenValue);
        return derivedRow;
    }
}
