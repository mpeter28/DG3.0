package org.finra.datagenerator.create;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.LinkedList;
import java.util.List;

public class ConstructRDD {

    public static JavaRDD<Integer> constructRDDOfSize(int powersOfTen, JavaSparkContext sc) {
        List<Integer> nElements = new LinkedList<>();
        nElements.add(0);
        JavaRDD<Integer> initial = sc.parallelize(nElements);

        for (int i = 1; i <= powersOfTen; i++) {
            initial = initial.flatMap(new NTimesFunction(10));
        }

        return initial;
    }

    public static class NTimesFunction implements FlatMapFunction<Integer, Integer> {

        private int nTimes;

        public NTimesFunction(int nTimes) {
            this.nTimes = nTimes;
        }

        @Override
        public Iterable<Integer> call(Integer current) throws Exception {
            List<Integer> tenElements = new LinkedList<>();
            int base = current * nTimes;

            for (int offset = 0; offset < nTimes; offset++)
                tenElements.add(base + offset);

            return tenElements;
        }
    }
}
