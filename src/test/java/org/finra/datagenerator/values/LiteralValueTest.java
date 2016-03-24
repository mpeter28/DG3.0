package org.finra.datagenerator.values;

import org.junit.Assert;
import org.junit.Test;

public class LiteralValueTest {

    @Test
    public void wrapsStringTest() {
        LiteralValue value = new LiteralValue("Lorem Ipsum");
        Assert.assertEquals("Lorem Ipsum", value.generateValue(0));
    }
}
