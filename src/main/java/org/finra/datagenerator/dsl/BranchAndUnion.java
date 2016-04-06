package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.util.Map;

public class BranchAndUnion implements GenerationStep {

    private Function<Map<String, String>, Boolean> condition;

    private GenerationStep processTrue;
    private GenerationStep processFalse;

    public BranchAndUnion(Function<Map<String, String>, Boolean> condition,
                          GenerationStep processTrue, GenerationStep processFalse) {
        this.condition = condition;
        this.processTrue = processTrue;
        this.processFalse = processFalse;
    }

    public JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork) {
        JavaRDD<Map<String, String>> trueWork = previousWork.filter(condition);
        JavaRDD<Map<String, String>> falseWork = previousWork.filter(row -> !condition.call(row));

        trueWork = processTrue.generate(trueWork);
        falseWork = processFalse.generate(falseWork);
        return trueWork.union(falseWork);
    }

}
