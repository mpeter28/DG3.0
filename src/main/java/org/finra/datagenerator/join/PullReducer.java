package org.finra.datagenerator.join;

import java.io.Serializable;

public interface PullReducer<MainType, RefType> extends Serializable {

    MainType reduceRows(MainType mainTableRow, RefType refTableRow);

}
