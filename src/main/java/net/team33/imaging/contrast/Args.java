package net.team33.imaging.contrast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.lang.String.format;

public class Args {

    private static final double MAX_FACTOR = 100.0;
    private static final int MAX_RADIUS = 1024;

    private final Path sourcePath;
    private final int radius;
    private final double factor;

    private Args(final Path sourcePath, final int radius, final double factor)
            throws NullPointerException, IllegalArgumentException {
        if ((0 < radius) && (radius <= MAX_RADIUS) && (0 < factor) && (factor <= MAX_FACTOR)) {
            this.sourcePath = sourcePath.toAbsolutePath().normalize();
            this.radius = radius;
            this.factor = factor;
        } else {
            throw new IllegalArgumentException(format(
                    "Required: ((0 < radius(%d) <= %d) && (0 < factor(%s) <= %s))",
                    radius, MAX_RADIUS, factor, MAX_FACTOR));
        }
    }

    public static Args build(final String[] args) throws Problem {
        try {
            return new Args(Paths.get(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]));
        } catch (RuntimeException caught) {
            throw new Problem(format("Problem while parsing arguments <%s>", Arrays.toString(args)), caught);
        }
    }

    private static Path outputPath(final Path sourcePath, String name) throws IOException {
        final String toString = sourcePath.toString();
        final int dotPos = toString.lastIndexOf('.');
        final Path dir = Paths.get(toString.substring(0, dotPos));
        Files.createDirectories(dir);
        return dir.resolve(name);
    }

    public Path getSourcePath() {
        return sourcePath;
    }

    public int getRadius() {
        return radius;
    }

    public double getFactor() {
        return factor;
    }

    @Override
    public final String toString() {
        return format("Args(sourcePath(%s), radius(%d), factor(%s)}", sourcePath, radius, factor);
    }

    public Path getMinimumPath() throws IOException {
        return outputPath(sourcePath, "minimum.png");
    }

    public Path getMediumPath() throws IOException {
        return outputPath(sourcePath, "medium.png");
    }

    public Path getMaximumPath() throws IOException {
        return outputPath(sourcePath, "maximum.png");
    }

    public static class Problem extends Exception {
        public Problem(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
