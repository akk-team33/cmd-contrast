package net.team33.imaging;

import net.team33.imaging.math.Dispersion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RGBImage {

    private static final Direction HORIZONTAL = (x, y, delta) -> new Point(x + delta, y);
    private static final Direction VERTICAL = (x, y, delta) -> new Point(x, y + delta);

    private final int width;
    private final int height;
    private final int type;
    private final int[] pixelValues;

    private RGBImage(final int width, final int height, final int type, final PixelSupplier supplier) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.pixelValues = new int[width * height * 3];
        final ExecutorService service = Executors.newWorkStealingPool();
        for (int y = 0; y < height; ++y) {
//            for (int x = 0; x < width; ++x) {
//                final int index = (y * width) + x;
//                final RGBPixel pixel = supplier.supply(index, width);
//                pixelValues[3 * index] = pixel.getRed();
//                pixelValues[(3 * index) + 1] = pixel.getGreen();
//                pixelValues[(3 * index) + 2] = pixel.getBlue();
//            }
            service.execute(new LineWise(y * width, supplier));
            if (0 == (y % 64)) {
                System.out.print('.');
            }
        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (final InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static RGBImage read(final Path path) throws IOException {
        return from(ImageIO.read(path.toFile()));
    }

    private static RGBImage from(final BufferedImage image) {
        return new RGBImage(
                image.getWidth(),
                image.getHeight(),
                image.getType(),
                (i, w) -> new RGBPixel(image.getRGB(i % w, i / w))
        );
    }

    public final void write(final Format format, final Path path) throws IOException {
        ImageIO.write(build(), format.getRawName(), path.toFile());
    }

    public final BufferedImage build() {
        final BufferedImage result = new BufferedImage(width, height, type);
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                result.setRGB(x, y, getPixel(x, y).toRgb());
            }
        }
        return result;
    }

    public final RGBImage blurred(final int radius) {
        try {
            final Dispersion dispersion = Dispersion.forRadius(radius);
            return blurred(HORIZONTAL, dispersion).blurred(VERTICAL, dispersion);
        } finally {
            System.out.println(" blurred ");
        }
    }

    private RGBImage blurred(final Direction direction, final Dispersion dispersion) {
        return new RGBImage(width, height, type, new Blurring(this, direction, dispersion));
    }

    public final RGBPixel getPixel(final int x, final int y) {
        final int index = x + (y * width);
        return new RGBPixel(
                pixelValues[3 * index],
                pixelValues[(3 * index) + 1],
                pixelValues[(3 * index) + 2]
        );
    }

    public final RGBPixel getPixel(final Point point) {
        return getPixel(point.x, point.y);
    }

    public final RGBImage enhanced(final int radius, final double intensity) {
        try {
            return new RGBImage(width, height, type, new Enhancing(this, blurred(radius), intensity));
        } finally {
            System.out.println(" enhanced ");
        }
    }

    @FunctionalInterface
    private interface PixelSupplier {
        RGBPixel supply(int flatIndex, int lineWidth);
    }

    @FunctionalInterface
    private interface Direction {
        Point point(int x, int y, int delta);
    }

    @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
    private static class Blurring implements PixelSupplier {
        private final RGBImage origin;
        private final Direction direction;
        private final Dispersion dispersion;

        private Blurring(final RGBImage origin, final Direction direction, final Dispersion dispersion) {
            this.origin = origin;
            this.direction = direction;
            this.dispersion = dispersion;
        }

        @Override
        public final RGBPixel supply(final int flatIndex, final int lineWidth) {
            final RGBPixel.Builder builder = RGBPixel.builder();
            for (int distance = dispersion.getMinDistance(), limit = dispersion.getEffectiveRadius();
                 distance <= limit; ++distance) {

                final Point p = direction.point(flatIndex % lineWidth, flatIndex / lineWidth, distance);
                if ((0 <= p.x) && (p.x < origin.width) && (0 <= p.y) && (p.y < origin.height)) {
                    builder.add(origin.getPixel(p), dispersion.getWeight(distance));
                }
            }
            return builder.build();
        }
    }

    private class LineWise implements Runnable {
        private final int startIndex;
        private final PixelSupplier supplier;

        private LineWise(final int startIndex, final PixelSupplier supplier) {
            this.startIndex = startIndex;
            this.supplier = supplier;
        }

        @Override
        public final void run() {
            for (int x = 0; x < width; ++x) {
                final int index = startIndex + x;
                final RGBPixel pixel = supplier.supply(index, width);
                pixelValues[3 * index] = pixel.getRed();
                pixelValues[(3 * index) + 1] = pixel.getGreen();
                pixelValues[(3 * index) + 2] = pixel.getBlue();
            }
        }
    }

    private class Enhancing implements PixelSupplier {
        private final RGBImage sharp;
        private final RGBImage blurred;
        private final double intensity;

        private Enhancing(final RGBImage sharp, final RGBImage blurred, final double intensity) {
            this.sharp = sharp;
            this.blurred = blurred;
            this.intensity = intensity;
        }

        @Override
        public RGBPixel supply(final int flatIndex, final int lineWidth) {
            final int x = flatIndex % lineWidth;
            final int y = flatIndex / lineWidth;
            return RGBPixel.enhanced(sharp.getPixel(x, y), blurred.getPixel(x, y), intensity);
        }
    }
}
