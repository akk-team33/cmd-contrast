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
        RESOURCE_PATH = Paths.get("src", "test", "resources", "MainTrial2.png").toAbsolutePath().normalize();
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
    public final void testBlurred020() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(20)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred020.png"));
    }

    @Test
    public final void testBlurred040() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(40)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred040.png"));
    }

    @Test
    public final void testBlurred080() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(80)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred080.png"));
    }

    @Test
    public final void testBlurred160() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(160)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred160.png"));
    }

    @Test
    public final void testEnhanced001_100() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(1, 1.0)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced001_100.png"));
    }

    @Test
    public final void testEnhanced002_090() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(2, 0.9)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced002_090.png"));
    }

    @Test
    public final void testEnhanced005_075() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(5, 0.75)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced005_075.png"));
    }

    @Test
    public final void testEnhanced010_050() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(10, 0.5)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced010_050.png"));
    }

    @Test
    public final void testEnhanced020_050() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(20, 0.5)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced020_050.png"));
    }

    @Test
    public final void testEnhanced040_050() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(40, 0.5)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced040_050.png"));
    }

    @Test
    public final void testEnhanced080_050() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(80, 0.5)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced080_050.png"));
    }

    @Test
    public final void testEnhanced160_050() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(160, 0.5)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced160_050.png"));
    }
}
