package net.team33.imaging.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class GaussDispersion {

    private static final BigInteger MAX_WEIGHT = BigInteger.valueOf(0x00ffffff);
    private static final double THRESHOLD = 10000.0;
    private static final double SQRT_2_PI = sqrt(2 * Math.PI);

    private final int nominalRadius; // standard deviation
    private final int effectiveRadius; // weights become irrelevant
    private final double[] weights;

    private GaussDispersion(final int nominalRadius) {
        this.nominalRadius = nominalRadius;
        final List<Double> bigWeights = new ArrayList<>(nominalRadius);
        final double max = gauss(0.0, nominalRadius);
        final double limit = max / THRESHOLD;
        double next = max;
        for (int distance = 1; next > limit; ++distance) {
            bigWeights.add(next);
            next = gauss(distance, nominalRadius);
        }
        effectiveRadius = bigWeights.size() - 1;
        weights = new double[bigWeights.size()];
        for (int index = 0; index < weights.length; ++index) {
            weights[index] = bigWeights.get(index);
        }
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

    public static GaussDispersion forRadius(final int radius) {
        return new GaussDispersion(radius);
    }

    private double gauss(final double x, final double sigma) {
        final double a = 1 / (sigma * SQRT_2_PI);
        final double b = pow(x, 2) / (2 * pow(sigma, 2));
        return a * exp(-b);
    }

    public final int getNominalRadius() {
        return nominalRadius;
    }

    public final int getEffectiveRadius() {
        return effectiveRadius;
    }

    public final double getWeight(final int distance) {
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
