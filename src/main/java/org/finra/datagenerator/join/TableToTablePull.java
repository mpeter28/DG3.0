package org.finra.datagenerator.join;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.finra.datagenerator.join.mapping.TableTableJoin;
import scala.Tuple2;

public class TableToTablePull<MainType, RefType> {

    private JavaRDD<RefType> refTable;
    private TableTableJoin pullCriteria;
    private PullReducer<MainType, RefType> reducer;

    public TableToTablePull(JavaRDD<RefType> refTable, TableTableJoin pullCriteria,
                            PullReducer<MainType, RefType> reducer) {
        this.refTable = refTable;
        this.pullCriteria = pullCriteria;
        this.reducer = reducer;
    }

    public JavaRDD<MainType> generate(JavaRDD<MainType> mainTable) {
        JavaPairRDD<MainType, Long> mainForJoin = mainTable.zipWithIndex();
        JavaPairRDD<RefType, Long> refForJoin = refTable.zipWithIndex();

        return mainForJoin.cartesian(refForJoin)
            .filter(joinCandidate -> pullCriteria.validMatch(joinCandidate._1()._2(),
                joinCandidate._2()._2()))
            .map(joinedPair -> new Tuple2<>(joinedPair._1()._1(), joinedPair._2()._1()))
            .map(joinedPair -> reducer.reduceRows(joinedPair._1(), joinedPair._2()));
    }

}
