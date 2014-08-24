package net.team33.imaging.contrast;

import net.team33.imaging.Blurring;
import net.team33.imaging.Image;
import net.team33.imaging.test.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BlurringTrial {

    private static final Path RESOURCE_PATH;
    private static final Path TEST_PATH;
    private static final Path ORIGINAL_PATH;

    static {
        RESOURCE_PATH = Paths.get("src", "test", "resources", "MainTrial2.png").toAbsolutePath().normalize();
        TEST_PATH = Paths.get("target", "test.io", BlurringTrial.class.getName());
        ORIGINAL_PATH = TEST_PATH.resolve("original.png");
    }

    @BeforeClass
    public static void doBeforeClass() throws IOException {
        Util.delete(TEST_PATH);
        Files.createDirectories(TEST_PATH);
        Files.copy(RESOURCE_PATH, ORIGINAL_PATH);
    }

    @Test
    public void testBlur() throws IOException {
        final Image original = new Image(ORIGINAL_PATH);
        final Image blurred = original.blurred();
        blurred.write("png", TEST_PATH.resolve("testBlur.png"));
    }

    @Test
    public void testBlur016() throws IOException {
        final BufferedImage original = ImageIO.read(ORIGINAL_PATH.toFile());
        final BufferedImage blurred = Blurring.blurred(original, 16);
        ImageIO.write(blurred, "png", TEST_PATH.resolve("testBlur016.png").toFile());
    }

    @Test
    public void testBlur256() throws IOException {
        final BufferedImage original = ImageIO.read(ORIGINAL_PATH.toFile());
        final BufferedImage blurred = Blurring.blurred(original, 256);
        ImageIO.write(blurred, "png", TEST_PATH.resolve("testBlur256.png").toFile());
    }
}
