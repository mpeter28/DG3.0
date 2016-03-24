package org.finra.datagenerator.assignment;

import org.finra.datagenerator.values.LiteralValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SingleAssignTest {

    @Test
    public void initialMapUnchangedTest() {
        SingleAssign setAssign = new SingleAssign("Variable_Name", new LiteralValue("Variable_Value"));
        Map<String, String> initialMap = new HashMap<>();

        Map<String, String> newMap = setAssign.call(initialMap);

        Assert.assertEquals(0, initialMap.size());
        Assert.assertNotSame(initialMap, newMap);
    }

    @Test
    public void setsVariableTest() {
        SingleAssign setAssign = new SingleAssign("Variable_Name", new LiteralValue("Variable_Value"));
        Map<String, String> initialMap = new HashMap<>();


        Map<String, String> newMap = setAssign.call(initialMap);

        Assert.assertEquals(1, newMap.size());
        Assert.assertEquals("Variable_Value", newMap.get("Variable_Name"));
    }

    @Test
    public void altConstructorTest() {
        SingleAssign setAssign = new SingleAssign("Foo", "Bar");
        Map<String, String> initialMap = new HashMap<>();

        Map<String, String> newMap = setAssign.call(initialMap);

        Assert.assertEquals(1, newMap.size());
        Assert.assertEquals("Bar", newMap.get("Foo"));
    }
}
