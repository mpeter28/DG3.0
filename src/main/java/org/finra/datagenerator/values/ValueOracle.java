package org.finra.datagenerator.values;

import java.io.Serializable;

public interface ValueOracle extends Serializable {

    String generateValue(long seed);

}
