package org.finra.datagenerator.format;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DelimiterFormatTest {

    @Test
    public void formatsTest() {
        DelimiterFormat format = new DelimiterFormat(',', new String[] {"A", "B", "C", "D"});
        Map<String, String> testRow = new HashMap<>();
        testRow.put("A", "1");
        testRow.put("B", "2");
        testRow.put("C", "3");
        testRow.put("D", "4");

        String formatted = format.call(testRow);
        Assert.assertEquals("1,2,3,4", formatted);
    }
}
