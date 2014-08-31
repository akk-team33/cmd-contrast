package net.team33.imaging.doubles;

import net.team33.imaging.test.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CircleTrial {

    private static final Path TEST_PATH
            = Paths.get("target", "test.io", CircleTrial.class.getName()).toAbsolutePath().normalize();
    private static final String PNG = "png";

    @BeforeClass
    public static void doBeforeClass() throws IOException {
        Util.delete(TEST_PATH);
        Files.createDirectories(TEST_PATH);
    }

    @Test
    public final void test() throws IOException {
        final Circle circle = new Circle(new Point(-90, 192), 300, false);
        final Graph graph = new Graph(circle.function());
        graph.write(TEST_PATH, "test", PNG);
    }
}
