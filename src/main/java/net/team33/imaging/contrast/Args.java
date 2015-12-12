package net.team33.imaging.contrast;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.String.format;

public class Args {

    private final Path sourcePath;
    private final List<Job> jobs;

    private Args(final Path sourcePath, final Set<Integer> radii) {
        this.sourcePath = sourcePath.toAbsolutePath().normalize();
        this.jobs = new ArrayList<>(radii.size());
        int prev = 0;
        for (final Integer radius : radii) {
            jobs.add(new Job(radius, prev, this.sourcePath.toString()));
            prev = radius;
        }
    }

    public static Args build(final String[] args) throws Problem {
        try {
            return build(Arrays.asList(args).iterator());
        } catch (RuntimeException caught) {
            throw new Problem(format("Problem while parsing arguments <%s>", Arrays.toString(args)), caught);
        }
    }

    private static Args build(final Iterator<String> iterator) {
        final String filename = iterator.next();
        final Set<Integer> radien = new TreeSet<>();
        while (iterator.hasNext()) {
            radien.add(Integer.parseInt(iterator.next()));
        }
        return new Args(Paths.get(filename).toAbsolutePath().normalize(), radien);
    }

    public Path getSourcePath() {
        return sourcePath;
    }

    public List<Job> getJobs() {
        return Collections.unmodifiableList(jobs);
    }

    @Override
    public final String toString() {
        return format("Args(sourcePath(%s), jobs(%s)}", sourcePath, jobs);
    }

    public static class Job {
        private final int radius;
        private final String blurredPath;
        private final String enhancedPath;

        private Job(final int radius, final int prevRadius, final String sourcePath) {
            this.radius = radius - prevRadius;
            final String format = String.format(
                    "%s (%%s@%d).png",
                    sourcePath.substring(0, sourcePath.lastIndexOf(".")), radius);
            this.blurredPath = String.format(format, "blurred");
            this.enhancedPath = String.format(format, "enhanced");
        }

        public int getRadius() {
            return radius;
        }

        @Override
        public final String toString() {
            return format("Job(radius(%d), blurredPath(%s)}", radius, blurredPath);
        }

        public Path getBlurredPath() {
            return Paths.get(blurredPath);
        }

        public Path getEnhancedPath() {
            return Paths.get(enhancedPath);
        }
    }

    public static class Problem extends Exception {
        public Problem(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
