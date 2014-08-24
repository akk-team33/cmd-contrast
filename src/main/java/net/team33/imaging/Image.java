package net.team33.imaging;

import net.team33.imaging.contrast.Rgb;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class Image {

    private static final Delta[] DELTAS = {
            new Delta(-1, -1, 1), new Delta(0, -1, 2), new Delta(1, -1, 1),
            new Delta(-1, 0, 2), new Delta(0, 0, 4), new Delta(1, 0, 2),
            new Delta(-1, 1, 1), new Delta(0, 1, 2), new Delta(1, 1, 1)
    };

    private final int width;
    private final int height;
    private final int type;
    private final Pixel[] pixels;

    public Image(final Path path) throws IOException {
        this(ImageIO.read(path.toFile()));
    }

    private Image(final BufferedImage bufferedImage) {
        this(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType(), (x, y) -> {
            final int rgb = bufferedImage.getRGB(x, y);
            return new Pixel(Rgb.red(rgb), Rgb.green(rgb), Rgb.blue(rgb));
        });
    }

    private Image(final int width, final int height, final int type) {
        this(width, height, type, (x, y) -> new Pixel());
    }

    private Image(final int width, final int height, final int type, final NewPixel newPixel) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.pixels = new Pixel[width * height];
        forEach((x, y) -> {
            pixels[x + (y * width)] = newPixel.apply(x, y);
        });
    }

    public Image blurred(final int radius) {
        if (0 == radius)
            return this;
        else
            return blurred().blurred(radius - 1);
    }

    public Image blurred() {
        final Image result = new Image(width, height, type);
        forEach((targetX, targetY) -> {
            for (final Delta delta : DELTAS) {
                final int originalX = targetX + delta.x;
                final int originalY = targetY + delta.y;
                if (0 <= originalX && originalX < width && 0 <= originalY && originalY < height) {
                    result.getPixel(targetX, targetY).add(this.getPixel(originalX, originalY), delta.factor);
                }
            }
        });
        return result;
    }

    private Pixel getPixel(final int x, final int y) {
        return pixels[x + (y * width)];
    }

    public void write(final String format, final Path path) throws IOException {
        final BufferedImage bufferedImage = new BufferedImage(width, height, type);
        forEach((x, y) -> {
            final int index = x + (y * width);
            final Pixel pixel = pixels[index];
            bufferedImage.setRGB(x, y, pixel.getRgb());
        });
        ImageIO.write(bufferedImage, format, path.toFile());
    }

    private void forEach(final XYConsumer consumer) {
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                consumer.accept(x, y);
            }
        }
    }

    @FunctionalInterface
    private interface NewPixel {
        Pixel apply(int x, int y);
    }

    @FunctionalInterface
    private interface XYConsumer {
        void accept(int x, int y);
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
