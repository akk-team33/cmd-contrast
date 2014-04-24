package net.team33.imaging.test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
    public static void delete(final Path path) {
        if (Files.exists(path)) {
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
    }
}
