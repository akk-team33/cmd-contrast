package net.team33.imaging;

import net.team33.imaging.contrast.Rgb;

public class Pixel {

    private int red;
    private int green;
    private int blue;
    private int factor;

    private Pixel(final int red, final int green, final int blue, int factor) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.factor = factor;
    }

    public Pixel(final int red, final int green, final int blue) {
        this(red, green, blue, 1);
    }

    public Pixel() {
        this(0, 0, 0, 0);
    }

    public int getRgb() {
        if (0 == factor) {
            return 0;
        } else {
            return Rgb.combination(red / factor, green / factor, blue / factor);
        }
    }

    public void add(final Pixel pixel, final int factor) {
        this.red += (pixel.red * factor);
        this.green += (pixel.green * factor);
        this.blue += (pixel.blue * factor);
        this.factor += (pixel.factor * factor);
    }
}
