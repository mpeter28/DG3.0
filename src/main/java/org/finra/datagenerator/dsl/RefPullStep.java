package org.finra.datagenerator.dsl;

import org.apache.spark.api.java.JavaRDD;
import org.finra.datagenerator.join.PullReducer;
import org.finra.datagenerator.join.TableToTablePull;
import org.finra.datagenerator.join.mapping.TableTableJoin;

import java.util.Map;

public class RefPullStep extends TableToTablePull<Map<String, String>,
    Map<String, String>> implements GenerationStep {

    public RefPullStep(JavaRDD<Map<String, String>> refTable, TableTableJoin pullCriteria,
                       PullReducer<Map<String, String>, Map<String, String>> reducer) {
        super(refTable, pullCriteria, reducer);
    }

}
