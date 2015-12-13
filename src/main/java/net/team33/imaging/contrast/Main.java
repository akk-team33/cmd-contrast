package net.team33.imaging.contrast;

import net.team33.imaging.Format;
import net.team33.imaging.RGBImage;

import java.io.IOException;
import java.util.List;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) {
        try {
            proceed(Args.build(args));
        } catch (final Args.Problem caught) {
            caught.printStackTrace();
        } catch (final IOException caught) {
            caught.printStackTrace();
        }
    }

    private static void proceed(final Args args) throws IOException {
        System.out.println(args);
        final long time0 = System.currentTimeMillis();
        proceed(
                RGBImage.read(args.getSourcePath())
                        .normalized()
                        .write(Format.PNG, args.getPath("normalized")),
                args);
        final long timeX = System.currentTimeMillis();
        System.out.println(String.format("time elapsed: %f sec.", (timeX - time0) / 1000.0));
    }

    private static void proceed(final RGBImage origin, final Args args) throws IOException {
        proceed(
                origin.smoothed(origin.blurred(16)).write(Format.PNG, args.getPath("smoothed")),
                args.getJobs());
    }

    private static void proceed(final RGBImage origin, final List<Args.Job> jobs) throws IOException {
        RGBImage prev = origin;
        for (final Args.Job job : jobs) {
            final RGBImage blurred = prev
                    .blurred(job.getRadius())
                    .write(Format.PNG, job.getPath("blurred"));
            final RGBImage normal = blurred
                    .normalized()
                    .write(Format.PNG, job.getPath("normalized"));
            origin
                    .enhanced(normal)
                    .write(Format.PNG, job.getPath("enhanced"));
            origin
                    .smoothed(normal)
                    .write(Format.PNG, job.getPath("smoothed"));
            prev = blurred;
        }
    }
}
