package net.team33.imaging;

import net.team33.imaging.test.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("JUnitTestMethodWithNoAssertions")
public class RGBImageTrial {

    private static final Path RESOURCE_PATH;
    private static final Path TEST_PATH;
    private static final Path ORIGINAL_PATH;

    static {
        RESOURCE_PATH = Paths.get("src", "test", "resources", "MainTrial.png").toAbsolutePath().normalize();
        TEST_PATH = Paths.get("target", "test.io", RGBImageTrial.class.getName());
        ORIGINAL_PATH = TEST_PATH.resolve("original.png");
    }

    @BeforeClass
    public static void doBeforeClass() throws IOException {
        Util.delete(TEST_PATH);
        Files.createDirectories(TEST_PATH);
        Files.copy(RESOURCE_PATH, ORIGINAL_PATH);
    }

    @Test
    public final void testReadWrite() throws IOException {
        final RGBImage sample = RGBImage.read(ORIGINAL_PATH);
        sample.write(Format.PNG, TEST_PATH.resolve("testReadWrite.png"));
    }

    @Test
    public final void testBlurred000() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(0)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred000.png"));
    }

    @Test
    public final void testBlurred001() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(1)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred001.png"));
    }

    @Test
    public final void testBlurred002() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(2)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred002.png"));
    }

    @Test
    public final void testBlurred005() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(5)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred005.png"));
    }

    @Test
    public final void testBlurred010() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(10)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred010.png"));
    }

    @Test
    public final void testBlurred040() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(40)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred040.png"));
    }

    @Test
    public final void testBlurred160() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(160)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred160.png"));
    }

    @Test
    public final void testBlurred640() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(640)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred640.png"));
    }

    @Test
    public final void testEnhanced015_100() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(15, 1.0)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced015_100.png"));
    }

    @Test
    public final void testEnhanced060_040() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(60, 0.4)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced060_040.png"));
    }

    @Test
    public final void testEnhanced060_090() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(60, 0.9)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced060_090.png"));
    }

    @Test
    public final void testEnhanced240_030() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(240, 0.3)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced240_030.png"));
    }

    @Test
    public final void testEnhanced240_050() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(240, 0.5)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced240_050.png"));
    }

    @Test
    public final void testEnhanced240_100() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(240, 1.0)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced240_100.png"));
    }

    @Test
    public final void testEnhanced500_025() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(500, 0.25)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced500_025.png"));
    }

    @Test
    public final void testEnhanced999_050() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(999, 0.5)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced999_050.png"));
    }

    @Test
    public final void testEnhanced999_030() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(999, 0.3)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced999_030.png"));
    }

    @Test
    public final void testEnhanced999_010() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(999, 0.1)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced999_010.png"));
    }
}
