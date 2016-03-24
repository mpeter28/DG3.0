package org.finra.datagenerator.assignment;

import org.finra.datagenerator.values.LiteralValue;
import org.finra.datagenerator.values.OraclePool;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SetAssignTest {

    @Test
    public void initialMapUnchangedTest() {
        OraclePool values = new OraclePool();
        values.add(new LiteralValue("1"));
        values.add(new LiteralValue("2"));
        values.add(new LiteralValue("3"));

        SetAssign assign = new SetAssign("Variable_Name", values);

        Map<String, String> initialMap = new HashMap<>();

        try {
            Iterable<Map<String, String>> newMaps = assign.call(initialMap);

            Assert.assertEquals(0, initialMap.size());
            for (Map<String, String> result : newMaps) {
                Assert.assertNotSame(initialMap, result);
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void valuesSetTest() {
        OraclePool values = new OraclePool();
        values.add(new LiteralValue("1"));
        values.add(new LiteralValue("2"));
        values.add(new LiteralValue("3"));

        SetAssign assign = new SetAssign("Variable_Name", values);
        Map<String, String> initialMap = new HashMap<>();

        try {
            Iterable<Map<String, String>> newMaps = assign.call(initialMap);
            Iterator<Map<String, String>> resultIterator = newMaps.iterator();

            Assert.assertEquals("1", resultIterator.next().get("Variable_Name"));
            Assert.assertEquals("2", resultIterator.next().get("Variable_Name"));
            Assert.assertEquals("3", resultIterator.next().get("Variable_Name"));
            Assert.assertFalse(resultIterator.hasNext());
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
