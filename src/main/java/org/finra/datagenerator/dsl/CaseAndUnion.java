package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CaseAndUnion implements GenerationStep {

    private List<Function<Map<String, String>, Boolean>> conditions;
    private List<GenerationStep> processTrues;

    public CaseAndUnion() {
        conditions = new LinkedList<>();
        processTrues = new LinkedList<>();
    }

    public CaseAndUnion nextCase(Function<Map<String, String>, Boolean> condition,
                                GenerationStep processTrue) {
        conditions.add(condition);
        processTrues.add(processTrue);
        return this;
    }

    public CaseAndUnion defaultCase(GenerationStep process) {
        conditions.add(rdd -> true);
        processTrues.add(process);
        return this;
    }

    public JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork) {
        JavaRDD<Map<String, String>> totalWork = null;
        JavaRDD<Map<String, String>> remainingWork = previousWork;

        for (int caseNum = 0; caseNum < conditions.size(); caseNum++) {
            Function<Map<String, String>, Boolean> caseCondition = conditions.get(caseNum);
            GenerationStep caseStep = processTrues.get(caseNum);

            JavaRDD<Map<String, String>> trueWork = remainingWork.filter(caseCondition);
            remainingWork = remainingWork.filter(row -> !caseCondition.call(row));

            JavaRDD<Map<String, String>> caseWork = caseStep.generate(trueWork);
            totalWork = totalWork == null ? caseWork : totalWork.union(caseWork);
        }

        return totalWork;
    }
}
