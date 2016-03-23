package org.finra.datagenerator.join.mapping;

import java.io.Serializable;

public interface TableTableJoin extends Serializable {

    boolean validMatch(long mainIndex, long refIndex);

}
