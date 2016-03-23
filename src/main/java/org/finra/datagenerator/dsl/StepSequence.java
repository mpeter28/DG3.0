package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;

import java.util.Map;

public class StepSequence implements GenerationStep {

    public JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork) {
        return previousWork;
    }

    public StepSequence nextStep(GenerationStep nextStep) {
        return new NonBaseStepSequence(this, nextStep);
    }

    public static class NonBaseStepSequence extends StepSequence {
        private GenerationStep firstStep;
        private GenerationStep secondStep;

        public NonBaseStepSequence(GenerationStep firstStep, GenerationStep secondStep) {
            this.firstStep = firstStep;
            this.secondStep = secondStep;
        }

        public JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork) {
            return secondStep.generate(firstStep.generate(previousWork));
        }
    }
}
