package net.team33.imaging;

import net.team33.imaging.doubles.Circle;
import net.team33.imaging.doubles.Point;

public class RGBPixel {

    private static final int RED_BITS = 0x00ff0000;
    private static final int GREEN_BITS = 0x0000ff00;
    private static final int BLUE_BITS = 0x000000ff;
    private static final int ALPHA_MAX = 0xff000000;
    private static final int VALUE_LIMIT = 0x10000;

    private final int red;
    private final int green;
    private final int blue;

    public RGBPixel(final int rgb) {
        this((rgb & RED_BITS) >> 8, rgb & GREEN_BITS, (rgb & BLUE_BITS) << 8);
    }

    public RGBPixel(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static RGBPixel enhanced(final RGBPixel sharp, final RGBPixel blurred, final double intensity) {
        //noinspection AccessingNonPublicFieldOfAnotherObject
        return new RGBPixel(
                enhanced(sharp.red, blurred.red, intensity),
                enhanced(sharp.green, blurred.green, intensity),
                enhanced(sharp.blue, blurred.blue, intensity)
        );
    }

    private static int enhanced(final int sharp, final int blurred, final double intensity) {
        final Point center = new Point((sharp < blurred) ? 0 : VALUE_LIMIT, blurred);
        final double radius = (sharp < blurred) ? blurred : (VALUE_LIMIT - blurred);
        final Circle circle = new Circle(center, radius, sharp > blurred);

        //noinspection NumericCastThatLosesPrecision
        return (int) ((circle.y(sharp) * intensity) + (sharp * (1.0 - intensity)));
    }

    public final int toRgb() {
        return ALPHA_MAX | ((red << 8) & RED_BITS) | (green & GREEN_BITS) | ((blue >> 8) & BLUE_BITS);
    }

    @SuppressWarnings({"AccessingNonPublicFieldOfAnotherObject", "ReturnOfThis"})
    public static class Builder {
        private long red = 0;
        private long green = 0;
        private long blue = 0;
        private long count = 0;

        public final Builder add(final RGBPixel pixel, final long weight) {
            red += pixel.red * weight;
            green += pixel.green * weight;
            blue += pixel.blue * weight;
            count += weight;
            return this;
        }

        public final RGBPixel build() {
            //noinspection NumericCastThatLosesPrecision
            return new RGBPixel(
                    (int) ((red + (count / 2)) / count),
                    (int) ((green + (count / 2)) / count),
                    (int) ((blue + (count / 2)) / count)
            );
        }
    }
}
