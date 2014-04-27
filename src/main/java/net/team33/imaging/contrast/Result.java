package net.team33.imaging.contrast;

import java.awt.image.BufferedImage;

public class Result {

    private final BufferedImage minimum;
    private final BufferedImage medium;
    private final BufferedImage maximum;
    private final BufferedImage destination;

    public Result(final BufferedImage image, final int width, final int height) {
        minimum = newBufferedImage(image);
        medium = newBufferedImage(image);
        maximum = newBufferedImage(image);
        destination = newBufferedImage(image);
    }

    private static BufferedImage newBufferedImage(final BufferedImage image) {
        return new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
    }

    public BufferedImage getMinimum() {
        return minimum;
    }

    public BufferedImage getMaximum() {
        return maximum;
    }

    public BufferedImage getMedium() {
        return medium;
    }

    public BufferedImage getDestination() {
        return destination;
    }
}
