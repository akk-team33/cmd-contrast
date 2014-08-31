package net.team33.imaging.doubles;

import net.team33.imaging.test.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GraphTrial {

    private static final Path TEST_PATH
            = Paths.get("target", "test.io", GraphTrial.class.getName()).toAbsolutePath().normalize();
    private static final String PNG = "png";

    @BeforeClass
    public static void doBeforeClass() throws IOException {
        Util.delete(TEST_PATH);
        Files.createDirectories(TEST_PATH);
    }

    @Test
    public final void test() throws IOException {
        StraightLine line = new StraightLine(0.5, 20.0);
        final Graph graph = new Graph(line.function());
        graph.write(TEST_PATH, "test", PNG);
    }
}
