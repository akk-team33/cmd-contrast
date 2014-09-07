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
        //RESOURCE_PATH = Paths.get("src", "test", "resources", "MainTrial.png").toAbsolutePath().normalize();
        RESOURCE_PATH = Paths
                .get("C:\\Users\\andi\\Pictures\\Alben\\Erinnerungen\\2014\\Lechweg\\Tag02\\Peng\\DSC_2963\\")
                .resolve("DSC_2963_0.png")
                .toAbsolutePath().normalize();
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
    public final void testBlurred004() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(4)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred004.png"));
    }

    @Test
    public final void testBlurred008() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(8)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred008.png"));
    }

    @Test
    public final void testBlurred016() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(16)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred016.png"));
    }

    @Test
    public final void testBlurred032() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(32)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred032.png"));
    }

    @Test
    public final void testBlurred064() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(64)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred064.png"));
    }

    @Test
    public final void testBlurred128() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .blurred(128)
                .write(Format.PNG, TEST_PATH.resolve("testBlurred128.png"));
    }

    @Test
    public final void testEnhanced007_100() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(7, 1.0)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced007_100.png"));
    }

    @Test
    public final void testEnhanced015_100() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(15, 1.0)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced015_100.png"));
    }

    @Test
    public final void testEnhanced030_080() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(30, 0.8)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced030_080.png"));
    }

    @Test
    public final void testEnhanced060_060() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(60, 0.6)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced060_060.png"));
    }

    @Test
    public final void testEnhanced120_040() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(120, 0.4)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced120_040.png"));
    }

    @Test
    public final void testEnhanced240_020() throws IOException {
        RGBImage.read(ORIGINAL_PATH)
                .enhanced(240, 0.2)
                .write(Format.PNG, TEST_PATH.resolve("testEnhanced240_020.png"));
    }
}
