package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;

import java.io.Serializable;
import java.util.Map;

public interface GenerationStep extends Serializable {

    JavaRDD<Map<String, String>> generate(JavaRDD<Map<String, String>> previousWork);

}
