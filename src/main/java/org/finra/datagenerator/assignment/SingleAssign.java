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
    public Map<String, String> call(Map<String, String> stringStringMap) throws Exception {
        HashMap<String, String> updatedMap = new HashMap<>(stringStringMap);
        updatedMap.put(variableName, variableValue.generateValue(0));
        return updatedMap;
    }
}
