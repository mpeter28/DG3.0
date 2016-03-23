package org.finra.datagenerator.join.mapping;

public class IndexMatchJoin implements TableTableJoin {

    @Override
    public boolean validMatch(long mainIndex, long refIndex) {
        return mainIndex == refIndex;
    }
}
