package net.team33.imaging;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import static net.team33.imaging.Deviation.MAX_RADIUS;

public class RGBImage {

    private static final Direction HORIZONTAL = (x, y, delta) -> new Point(x + delta, y);
    private static final Direction VERTICAL = (x, y, delta) -> new Point(x, y + delta);

    private final int width;
    private final int height;
    private final int type;
    private final RGBPixel[] pixels;

    private RGBImage(final int width, final int height, final int type, final PixelSupplier supplier) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.pixels = new RGBPixel[width * height];
//        final ExecutorService service = Executors.newWorkStealingPool();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                final int index = (y * width) + x;
                pixels[index] = supplier.supply(index, width);
            }
            if (0 == (y % 64))
                System.out.print('.');
//            service.execute(new LineWise(y * width, supplier));
        }
//        service.shutdown();
//        try {
//            service.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (final InterruptedException e) {
//            throw new IllegalStateException(e);
//        }
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
        for (int index = 0; index < pixels.length; ++index) {
            result.setRGB(index % width, index / width, pixels[index].toRgb());
        }
        return result;
    }

    public final RGBImage blurred(final int radius) {
        try {
            if (MAX_RADIUS < radius) {
                //noinspection TailRecursion
                return blurred(MAX_RADIUS).blurred(radius - MAX_RADIUS);
            } else {
                return blurred(HORIZONTAL, Deviation.values(radius)).blurred(VERTICAL, Deviation.values(radius));
            }
        } finally {
            System.out.println('+');
            System.gc();
        }
    }

    private RGBImage blurred(final Direction direction, final Deviation[] deviations) {
        try {
            return new RGBImage(width, height, type, new Blurring(this, direction, deviations));
        } finally {
            System.out.println('-');
            //System.gc();
        }
    }

    public final RGBPixel getPixel(final int x, final int y) {
        return pixels[x + (y * width)];
    }

    public final RGBPixel getPixel(final Point point) {
        return getPixel(point.x, point.y);
    }

    public final RGBImage enhanced(final int radius, final double intensity) {
        try {
            return new RGBImage(width, height, type, new Enhancing(this, blurred(radius), intensity));
        } finally {
            System.out.println("*");
            System.gc();
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
        private final Deviation[] deviations;

        private Blurring(final RGBImage origin, final Direction direction, final Deviation[] deviations) {
            this.origin = origin;
            this.direction = direction;
            this.deviations = deviations;
        }

        @Override
        public final RGBPixel supply(final int flatIndex, final int lineWidth) {
            final RGBPixel.Builder builder = RGBPixel.builder();
            for (final Deviation deviation : deviations) {
                final Point point = direction.point(flatIndex % lineWidth, flatIndex / lineWidth, deviation.getDelta());
                if ((0 <= point.x) && (point.x < origin.width) && (0 <= point.y) && (point.y < origin.height)) {
                    builder.add(origin.getPixel(point), deviation.getWeight());
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
                pixels[index] = supplier.supply(index, width);
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
