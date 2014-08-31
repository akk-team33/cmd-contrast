package net.team33.imaging.contrast;

import net.team33.imaging.doubles.Circle;
import net.team33.imaging.doubles.Point;

import java.awt.image.BufferedImage;
import java.util.Random;

public class PixelInfo {

    private static final int VALUE_LIMIT = 256;

    private final Component red;
    private final Component green;
    private final Component blue;

    public PixelInfo(
            final Args args, final BufferedImage image, final int x0, final int y0, final int width, final int height) {

        final int[] redHistogram = new int[VALUE_LIMIT];
        final int[] greenHistogram = new int[VALUE_LIMIT];
        final int[] blueHistogram = new int[VALUE_LIMIT];

        final Random random = new Random();

        for (int x = x0 - args.getRadius(), xMax = x0 + args.getRadius(); x <= xMax; ++x) {
            for (int y = y0 - args.getRadius(), yMax = y0 + args.getRadius(); y <= yMax; ++y) {
                if ((0 <= x) && (x < width) && (0 <= y) && (y < height)) {
                    if ((square(x - x0) + square(y - y0)) <= square(random.nextInt(args.getRadius()))) {
                        final int rgb = image.getRGB(x, y);
                        redHistogram[Rgb.red(rgb)] += 1;
                        greenHistogram[Rgb.green(rgb)] += 1;
                        blueHistogram[Rgb.blue(rgb)] += 1;
                    }
                }
            }
        }

        final int rgb0 = image.getRGB(x0, y0);
        red = new Component(Rgb.red(rgb0), redHistogram);
        green = new Component(Rgb.green(rgb0), greenHistogram);
        blue = new Component(Rgb.blue(rgb0), blueHistogram);
    }

    public static PixelInfo valueOf(
            final Args args, final BufferedImage image, final int x, final int y, final int width, final int height) {

        return new PixelInfo(args, image, x, y, width, height);
    }

    private long square(final long base) {
        return base * base;
    }

    public int getMinimum() {
        return Rgb.combination(red.minimum, green.minimum, blue.minimum);
    }

    public int getMedium() {
        return Rgb.combination(red.medium, green.medium, blue.medium);
    }

    public int getMaximum() {
        return Rgb.combination(red.maximum, green.maximum, blue.maximum);
    }

    public int getDestination() {
        return Rgb.combination(red.destination, green.destination, blue.destination);
    }

    private static class Component {
        private final int minimum;
        private final int medium;
        private final int maximum;
        private final int destination;

        private Component(final int original, final int[] histogram) {
            final int steps = 2;
            this.medium = medium(histogram);
            this.minimum = minimum(histogram, medium, steps);
            this.maximum = maximum(histogram, medium, steps);


            final Point center = new Point((original < medium) ? 0 : VALUE_LIMIT, medium);
            final double radius = (original < medium) ? medium : (VALUE_LIMIT - medium);
            final Circle circle = new Circle(center, radius, original > medium);

            this.destination = (int) circle.y(original);
        }

        private static int maximal(final int original, final int steps) {
            if (1 > steps) {
                return original;
            } else {
                return maximal(medial(original, VALUE_LIMIT), steps - 1);
            }
        }

        private static int minimal(final int original, final int steps) {
            if (1 > steps) {
                return original;
            } else {
                return minimal(medial(0, original), steps - 1);
            }
        }

        private static int medial(final int low, final int high) {
            return (low + high) / 2;
        }

        private static int medium(final int[] histogram) {
            return medium(histogram, 0, VALUE_LIMIT, VALUE_LIMIT / 2);
        }

        private static int minimum(final int[] histogram, final int medium, final int step) {
            if (1 > step) {
                return medium;
            } else {
                return minimum(histogram, medium(histogram, 0, medium - 1, medium), step - 1);
            }
        }

        private static int maximum(final int[] histogram, final int medium, final int step) {
            if (1 > step) {
                return medium;
            } else {
                return maximum(histogram, medium(histogram, medium + 1, VALUE_LIMIT, medium), step - 1);
            }
        }

        private static int medium(final int[] histogram, final int min, final int max, final int fallback) {
            int result = 0;
            int count = 0;
            for (int value = min; value < max; ++value) {
                count += histogram[value];
                result += value * histogram[value];
            }
            return (0 < count) ? (result / count) : fallback;
        }

        private double delta(final double maximal, final double minimal) {
            return maximal - minimal;
        }
    }
}
