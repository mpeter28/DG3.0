package org.finra.datagenerator.values;

public class LiteralValue implements ValueOracle {

    private String literalValue;

    public LiteralValue(String literalValue) {
        this.literalValue = literalValue;
    }

    public String generateValue(long seed) {
        return literalValue;
    }
}
