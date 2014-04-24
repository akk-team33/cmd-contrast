package net.team33.imaging.contrast;

import org.junit.Assert;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class RgbTest {

    private static void testImage(final Combiner combiner, final Separator forX, final Separator forY)
            throws IOException {

        final BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 256; ++y) {
            for (int x = 0; x < 256; ++x) {
                image.setRGB(x, y, combiner.combination(x, y));
                Assert.assertEquals("for X", x, forX.extract(image.getRGB(x, y)));
                Assert.assertEquals("for Y", y, forY.extract(image.getRGB(x, y)));
            }
        }
    }

    @Test
    public void testRedGreen() throws IOException {
        testImage((x, y) -> Rgb.combination(x, y, 0), Rgb::red, Rgb::green);
    }

    @Test
    public void testRedBlue() throws IOException {
        testImage((x, y) -> Rgb.combination(x, 0, y), Rgb::red, Rgb::blue);
    }

    @Test
    public void testGreenBlue() throws IOException {
        testImage((x, y) -> Rgb.combination(0, x, y), Rgb::green, Rgb::blue);
    }

    @FunctionalInterface
    private interface Separator {
        int extract(int rgb);
    }

    @FunctionalInterface
    private interface Combiner {
        int combination(int x, int y);
    }
}
