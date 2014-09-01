package net.team33.imaging.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Dispersion {

    private static final BigInteger MAX_WEIGHT = BigInteger.valueOf(0x00ffffff);

    private final int nominalRadius;
    private final int effectiveRadius;
    private final int[] weights;

    private Dispersion(final int nominalRadius) {
        this.nominalRadius = nominalRadius;
        final List<BigInteger> bigWeights = new ArrayList<>(nominalRadius + 1);
        for (int k = nominalRadius, n = 2 * nominalRadius; k <= n; ++k) {
            bigWeights.add(binomialCoefficient(n, k));
        }
        final int compression = compression(bigWeights.get(0));
        weights = new int[nominalRadius + 1];
        int rr = 0;
        for (int i = 0; i < weights.length; ++i) {
            final BigInteger bigWeight = bigWeights.get(i).shiftRight(compression);
            weights[i] = bigWeight.intValue();
            if (0 < weights[i]) {
                rr = i;
            }
        }
        effectiveRadius = rr;
    }

    private static int compression(final BigInteger reference) {
        BigInteger compressed = reference;
        int result = 0;
        while (0 < compressed.compareTo(MAX_WEIGHT)) {
            compressed = compressed.shiftRight(1);
            result += 1;
        }
        return result;
    }

    private static BigInteger binomialCoefficient(final int n, final int k) {
        return binomialCoefficient(BigInteger.valueOf(n), BigInteger.valueOf(k));
    }

    private static BigInteger binomialCoefficient(final BigInteger n, final BigInteger k) {
        return factorial(n).divide(factorial(n.subtract(k)).multiply(factorial(k)));
    }

    private static BigInteger factorial(final BigInteger n) {
        if (0 <= BigInteger.ONE.compareTo(n)) {
            return BigInteger.ONE;
        } else {
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
        }
    }

    public static Dispersion forRadius(final int radius) {
        return new Dispersion(radius);
    }

    public final int getNominalRadius() {
        return nominalRadius;
    }

    public final int getEffectiveRadius() {
        return effectiveRadius;
    }

    public final int getWeight(final int distance) {
        if (0 > distance) {
            //noinspection TailRecursion
            return getWeight(-distance);

        } else if (weights.length > distance) {
            return weights[distance];

        } else {
            return 0;
        }
    }

    public final int getMinDistance() {
        return -effectiveRadius;
    }
}
