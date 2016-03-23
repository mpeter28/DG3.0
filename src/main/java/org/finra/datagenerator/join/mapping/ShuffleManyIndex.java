package org.finra.datagenerator.join.mapping;

public class ShuffleManyIndex implements TableTableJoin {

    private long shuffleSeed;
    private TableTableJoin join;

    public ShuffleManyIndex(long shuffleSeed, TableTableJoin join) {
        this.shuffleSeed = shuffleSeed;
        this.join = join;
    }

    @Override
    public boolean validMatch(long mainIndex, long refIndex) {
        mainIndex = mainIndex ^ shuffleSeed;
        mainIndex = (mainIndex >>> 10) | (mainIndex << (Long.SIZE - 10));

        return join.validMatch(mainIndex, refIndex);
    }
}
