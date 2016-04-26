package org.finra.datagenerator.values.eq;

import org.finra.datagenerator.values.ValueOracle;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NumericValue implements ValueOracle {

    private int wholeDigits;
    private int scale;

    public NumericValue(int precision, int scale) {
        this.wholeDigits = precision - scale;
        this.scale = scale;
    }

    @Override
    public String generateValue(long seed) {
        StringBuilder builtNumber = new StringBuilder();
        Random random = ThreadLocalRandom.current();
        random.setSeed(seed);

        if (wholeDigits == 0 && scale > 0) {
            builtNumber.append("0.");
            digitSequence(builtNumber, random, scale);
        } else if (wholeDigits > 0) {
            builtNumber.append(random.nextInt(9) + 1);
            digitSequence(builtNumber, random, wholeDigits - 1);
            if (scale > 0) {
                builtNumber.append(".");
                digitSequence(builtNumber, random, scale);
            }
        }

        return builtNumber.toString();
    }

    private void digitSequence(StringBuilder b, Random random, int numberOfDigits) {
        for (int i = 0; i < numberOfDigits; i++) {
            b.append(random.nextInt(10));
        }
    }
}
