package net.team33.imaging.contrast;

import java.awt.image.BufferedImage;

public class PixelInfo {

    private static final int VALUE_LIMIT = 256;

    private final int minimumRed;
    private final int minimumGreen;
    private final int minimumBlue;

    private final int mediumRed;
    private final int mediumGreen;
    private final int mediumBlue;

    private final int maximumRed;
    private final int maximumGreen;
    private final int maximumBlue;

    public PixelInfo(final Args args, final BufferedImage image, final int x0, final int y0, final int width, final int height) {
        final int[] red = new int[VALUE_LIMIT];
        final int[] green = new int[VALUE_LIMIT];
        final int[] blue = new int[VALUE_LIMIT];

        for (int x = x0 - args.getRadius(), xMax = x0 + args.getRadius(); x <= xMax; ++x) {
            for (int y = y0 - args.getRadius(), yMax = y0 + args.getRadius(); y <= yMax; ++y) {
                if ((0 <= x) && (x < width) && (0 <= y) && (y < height)) {
                    if ((square(x - x0) + square(y - y0)) <= square(args.getRadius())) {
                        final int rgb = image.getRGB(x, y);
                        red[Rgb.red(rgb)] += 1;
                        green[Rgb.green(rgb)] += 1;
                        blue[Rgb.blue(rgb)] += 1;
                    }
                }
            }
        }

        mediumRed = medium(red);
        mediumGreen = medium(green);
        mediumBlue = medium(blue);

        maximumRed = maximum(red, mediumRed);
        maximumGreen = maximum(green, mediumGreen);
        maximumBlue = maximum(blue, mediumBlue);

        minimumRed = minimum(red, mediumRed);
        minimumGreen = minimum(green, mediumGreen);
        minimumBlue = minimum(blue, mediumBlue);
    }

    private static int minimum(final int[] histogram, final int medium) {
        return medium(histogram, 0, medium + 1);
    }

    private static int maximum(final int[] histogram, final int medium) {
        return medium(histogram, medium + 1, VALUE_LIMIT);
    }

    private static int medium(final int[] histogram) {
        return medium(histogram, 0, VALUE_LIMIT);
    }

    private static int medium(final int[] histogram, final int min, final int max) {
        int result = 0;
        int count = 0;
        for (int value = min; value < max; ++value) {
            count += histogram[value];
            result += value * histogram[value];
        }
        return result / count;
    }

    public static PixelInfo valueOf(
            final Args args, final BufferedImage image, final int x, final int y, final int width, final int height) {

        return new PixelInfo(args, image, x, y, width, height);
    }

    private long square(final long base) {
        return base * base;
    }

    public int getMinimum() {
        return Rgb.combination(minimumRed, minimumGreen, minimumBlue);
    }

    public int getMedium() {
        return Rgb.combination(mediumRed, mediumGreen, mediumBlue);
    }

    public int getMaximum() {
        return Rgb.combination(maximumRed, maximumGreen, maximumBlue);
    }
}
