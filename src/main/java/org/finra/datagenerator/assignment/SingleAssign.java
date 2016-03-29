package org.finra.datagenerator.assignment;

import org.apache.spark.api.java.function.Function;
import org.finra.datagenerator.values.LiteralValue;
import org.finra.datagenerator.values.ValueOracle;

import java.util.HashMap;
import java.util.Map;

public class SingleAssign implements Function<Map<String, String>, Map<String, String>> {

    private String variableName;
    private ValueOracle variableValue;

    public SingleAssign(String variableName, ValueOracle variableValue) {
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    public SingleAssign(String variableName, String variableValue) {
        this.variableName = variableName;
        this.variableValue = new LiteralValue(variableValue);
    }

    @Override
    public Map<String, String> call(Map<String, String> row) {
        HashMap<String, String> updateRow = new HashMap<>(row);
        int seed = row.hashCode();
        updateRow.put(variableName, variableValue.generateValue(seed));
        return updateRow;
    }
}
