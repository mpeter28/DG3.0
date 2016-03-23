package org.finra.datagenerator.values;

import java.util.LinkedList;
import java.util.List;

public class OraclePool implements ValuePool {

    private List<ValueOracle> oracles;

    public OraclePool() {
        oracles = new LinkedList<>();
    }

    public OraclePool add(ValueOracle oracle) {
        oracles.add(oracle);
        return this;
    }

    public OraclePool add(String literal) {
        oracles.add(new LiteralValue(literal));
        return this;
    }

    @Override
    public List<String> values(long seed) {
        List<String> values = new LinkedList<>();
        for (ValueOracle oracle : oracles) {
            values.add(oracle.generateValue(seed));
        }

        return values;
    }
}
