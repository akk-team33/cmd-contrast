package net.team33.imaging.contrast;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.team33.imaging.contrast.Main.main;

public class MainTrial {

    private static final Path RESOURCE_PATH
            = Paths.get("src", "test", "resources", "MainTrial.jpg").toAbsolutePath().normalize();
    private static final Path TEST_PATH
            = Paths.get("target", "test.io", MainTrial.class.getName());
    private static final String TEST_MAIN99_IMG = "testMain99.jpg";

    @BeforeClass
    public static void doBeforeClass() throws IOException {
        Path path = TEST_PATH.toAbsolutePath().normalize();
        delete(path);
        Files.createDirectories(path);
    }

    private static void delete(final Path path) {
        try {
            if (Files.isDirectory(path)) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                    stream.forEach((entry) -> delete(entry));
                }
            }
            Files.delete(path);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    @Test
    public void testMain00() throws Exception {
        main(new String[0]);
    }

    @Test
    public void testMain01() throws Exception {
        main(new String[1]);
    }

    @Test
    public void testMain02() throws Exception {
        main(new String[2]);
    }

    @Test
    public void testMain03() throws Exception {
        main(new String[3]);
    }

    @Test
    public void testMain04() throws Exception {
        main(new String[4]);
    }

    @Test
    public void testMain99() throws Exception {
        Path path = TEST_PATH.resolve(TEST_MAIN99_IMG);
        Files.copy(RESOURCE_PATH, path);
        main(new String[]{path.toString(), "256", "100"});
    }
}
