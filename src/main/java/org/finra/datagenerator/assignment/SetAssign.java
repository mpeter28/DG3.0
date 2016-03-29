package org.finra.datagenerator.assignment;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.finra.datagenerator.values.ValuePool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SetAssign implements FlatMapFunction<Map<String, String>, Map<String, String>> {

    private String variableName;
    private ValuePool values;

    public SetAssign(String variableName, ValuePool values) {
        this.variableName = variableName;
        this.values = values;
    }

    @Override
    public Iterable<Map<String, String>> call(Map<String, String> row) {
        int seed = row.hashCode();
        List<Map<String, String>> derivedRows = new LinkedList<>();

        for (String variableValue : values.values(seed)) {
            Map<String, String> derivedRow = new HashMap<>(row);
            derivedRow.put(variableName, variableValue);
            derivedRows.add(derivedRow);
        }

        return derivedRows;
    }
}
