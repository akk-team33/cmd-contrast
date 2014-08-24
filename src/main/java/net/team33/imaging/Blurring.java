package net.team33.imaging;

import net.team33.imaging.contrast.Rgb;

import java.awt.image.BufferedImage;

public class Blurring {

    private static final Delta[] DELTAS = {
            new Delta(-1, -1, 1), new Delta(0, -1, 2), new Delta(1, -1, 1),
            new Delta(-1, 0, 2), new Delta(0, 0, 4), new Delta(1, 0, 2),
            new Delta(-1, 1, 1), new Delta(0, 1, 2), new Delta(1, 1, 1)
    };

    public static BufferedImage blurred(final BufferedImage original, final int radius) {
        if (0 == radius) {
            return original;
        } else {
            return blurred(blurred(original), radius - 1);
        }
    }

    public static BufferedImage blurred(final BufferedImage original) {
        final int width = original.getWidth();
        final int height = original.getHeight();
        final BufferedImage result = new BufferedImage(width, height, original.getType());
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                blur(original, x, y, width, height, result);
            }
        }
        return result;
    }

    private static void blur(final BufferedImage original,
                             final int targetX, final int targetY,
                             final int width, final int height,
                             final BufferedImage result) {
        int red = 0, green = 0, blue = 0, count = 0;
        for (final Delta delta : DELTAS) {
            final int originalX = targetX + delta.x;
            final int originalY = targetY + delta.y;
            if (0 <= originalX && originalX < width && 0 <= originalY && originalY < height) {
                final int rgb = original.getRGB(originalX, originalY);
                red += Rgb.red(rgb) * delta.factor;
                green += Rgb.green(rgb) * delta.factor;
                blue += Rgb.blue(rgb) * delta.factor;
                count += delta.factor;
            }
        }
        red /= count;
        green /= count;
        blue /= count;
        result.setRGB(targetX, targetY, Rgb.combination(red, green, blue));
    }

    private static class Delta {
        private final int x;
        private final int y;
        private final int factor;

        private Delta(final int x, final int y, final int factor) {
            this.x = x;
            this.y = y;
            this.factor = factor;
        }
    }
}
