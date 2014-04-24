package net.team33.imaging.test;

import net.team33.imaging.contrast.Rgb;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageTrial {

    private static final Path TEST_PATH
            = Paths.get("target", "test.io", ImageTrial.class.getName());

    @BeforeClass
    public static void doBeforeClass() throws IOException {
        final Path path = TEST_PATH.toAbsolutePath().normalize();
        Util.delete(path);
        Files.createDirectories(path);
    }

    private static void testImage(final Combiner combiner, final String name) throws IOException {
        final BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 256; ++y) {
            for (int x = 0; x < 256; ++x) {
                image.setRGB(x, y, combiner.combination(x, y));
            }
        }
        ImageIO.write(image, "png", TEST_PATH.resolve(name).toFile());
    }

    @Test
    public void testRedGreen() throws IOException {
        testImage((x, y) -> Rgb.combination(x, y, 0), "testRedGreen.png");
    }

    @Test
    public void testRedBlue() throws IOException {
        testImage((x, y) -> Rgb.combination(x, 0, y), "testRedBlue.png");
    }

    @Test
    public void testGreenBlue() throws IOException {
        testImage((x, y) -> Rgb.combination(0, x, y), "testGreenBlue.png");
    }

    @FunctionalInterface
    private interface Combiner {
        int combination(int x, int y);
    }
}
