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

    private final String sourcePath;
    private final List<Job> jobs;
    private final String outPath;

    private Args(final Path sourcePath, final Set<Integer> radii) {
        this.sourcePath = sourcePath.toAbsolutePath().normalize().toString();
        this.outPath = String.format(
                "%s (%%s).png",
                this.sourcePath.substring(0, this.sourcePath.lastIndexOf(".")));
        this.jobs = new ArrayList<>(radii.size());
        int prev = 0;
        for (final Integer radius : radii) {
            jobs.add(new Job(radius, prev, this.outPath));
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
        return Paths.get(sourcePath);
    }

    public Path getPath(final String job) {
        return Paths.get(String.format(outPath, job));
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
        private final String outPath;

        private Job(final int radius, final int prevRadius, final String outPath) {
            this.radius = radius - prevRadius;
            this.outPath = String.format(
                    outPath,
                    String.format("%%s@%d", radius));
        }

        public int getRadius() {
            return radius;
        }

        @Override
        public final String toString() {
            return format("Job(radius(%d), outPath(%s)}", radius, outPath);
        }

        public Path getPath(final String job) {
            return Paths.get(String.format(outPath, job));
        }
    }

    public static class Problem extends Exception {
        public Problem(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
