package org.finra.datagenerator.create;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.LinkedList;
import java.util.List;

public class ConstructRDD {

    public static JavaRDD<Integer> constructRDDOfSize(int n, int powerOfTen, JavaSparkContext sc) {
        List<Integer> nElements = new LinkedList<>();
        for (int count = 0; count < n; count++)
            nElements.add(1);
        JavaRDD<Integer> initial = sc.parallelize(nElements);

        for (int i = 0; i < powerOfTen; i++) {
            initial = initial.flatMap(new FlatMapFunction<Integer, Integer>() {
                @Override
                public Iterable<Integer> call(Integer integer) throws Exception {
                    List<Integer> tenElements = new LinkedList<>();
                    for (int count = 0; count < 10; count++)
                        tenElements.add(1);
                    return tenElements;
                }
            });
        }

        return initial;
    }
}
