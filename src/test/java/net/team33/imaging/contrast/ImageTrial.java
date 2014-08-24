package net.team33.imaging.contrast;

import net.team33.imaging.Image;
import net.team33.imaging.test.Util;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageTrial {

    private static final Path RESOURCE_PATH;
    private static final Path TEST_PATH;
    private static final Path ORIGINAL_PATH;

    static {
        RESOURCE_PATH = Paths.get("src", "test", "resources", "MainTrial2.png").toAbsolutePath().normalize();
        TEST_PATH = Paths.get("target", "test.io", ImageTrial.class.getName());
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
    public void testBlur002() throws IOException {
        final Image original = new Image(ORIGINAL_PATH);
        final Image blurred = original.blurred(2);
        blurred.write("png", TEST_PATH.resolve("testBlur002.png"));
    }

    @Test
    public void testBlur003() throws IOException {
        final Image original = new Image(ORIGINAL_PATH);
        final Image blurred = original.blurred(3);
        blurred.write("png", TEST_PATH.resolve("testBlur003.png"));
    }

    @Test
    public void testBlur004() throws IOException {
        final Image original = new Image(ORIGINAL_PATH);
        final Image blurred = original.blurred(4);
        blurred.write("png", TEST_PATH.resolve("testBlur004.png"));
    }

    @Test
    public void testBlur008() throws IOException {
        final Image original = new Image(ORIGINAL_PATH);
        final Image blurred = original.blurred(8);
        blurred.write("png", TEST_PATH.resolve("testBlur008.png"));
    }

    @Test
    public void testBlur016() throws IOException {
        final Image original = new Image(ORIGINAL_PATH);
        final Image blurred = original.blurred(16);
        blurred.write("png", TEST_PATH.resolve("testBlur016.png"));
    }

    @Ignore
    @Test
    public void testBlur256() throws IOException {
        final Image original = new Image(ORIGINAL_PATH);
        final Image blurred = original.blurred(256);
        blurred.write("png", TEST_PATH.resolve("testBlur256.png"));
    }
}
