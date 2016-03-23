package org.finra.datagenerator.join.mapping;

public class ModuloManyOnto implements TableTableJoin {

    private long refTableSize;
    private TableTableJoin join;

    public ModuloManyOnto(long refTableSize, TableTableJoin join) {
        this.refTableSize = refTableSize;
        this.join = join;
    }

    public ModuloManyOnto(long refTableSize) {
        this.refTableSize = refTableSize;
        this.join = new IndexMatchJoin();
    }

    @Override
    public boolean validMatch(long mainIndex, long refIndex) {
        return join.validMatch(mainIndex % refTableSize, refIndex);
    }
}
