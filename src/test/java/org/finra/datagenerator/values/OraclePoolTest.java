package org.finra.datagenerator.values;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class OraclePoolTest {

    @Test
    public void addTests() {
        OraclePool values = new OraclePool();
        values.add("A");
        values.add(new LiteralValue("B"));
        values.add("C");
        values.add(new LiteralValue("D"));

        List<String> generate = values.values(0);
        Assert.assertEquals(4, generate.size());
        Assert.assertEquals("A", generate.get(0));
        Assert.assertEquals("B", generate.get(1));
        Assert.assertEquals("C", generate.get(2));
        Assert.assertEquals("D", generate.get(3));
    }

    @Test
    public void passesSeedCorrectlyTest() {
        final AtomicLong passedSeed = new AtomicLong(-1);
        ValueOracle mockValue = new ValueOracle() {
            @Override
            public String generateValue(long seed) {
                passedSeed.set(seed);
                return "";
            }
        };

        OraclePool values = new OraclePool();
        values.add(mockValue);
        values.values(22);

        Assert.assertEquals(22, passedSeed.get());
    }
}
