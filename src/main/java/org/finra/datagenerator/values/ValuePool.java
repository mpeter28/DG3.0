package org.finra.datagenerator.values;

import java.io.Serializable;
import java.util.List;

public interface ValuePool extends Serializable {

    List<String> values(long seed);

}
