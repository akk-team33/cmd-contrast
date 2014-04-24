package net.team33.imaging.contrast;

public class Rgb {

    public static int combination(final int red, final int green, final int blue) {
        return 0xff000000 | (red << 16) | (green << 8) | blue;
    }

    public static int red(final int rgb) {
        return (rgb & 0x00ff0000) >> 16;
    }

    public static int green(final int rgb) {
        return (rgb & 0x0000ff00) >> 8;
    }

    public static int blue(final int rgb) {
        return (rgb & 0x000000ff);
    }
}
