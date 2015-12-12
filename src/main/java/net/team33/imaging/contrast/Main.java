package net.team33.imaging.contrast;

import net.team33.imaging.Format;
import net.team33.imaging.RGBImage;

import java.io.IOException;
import java.util.List;

// 73948 (14:18)
// 327272 (14:01)

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
        proceed(RGBImage.read(args.getSourcePath()), args.getJobs());
    }

    private static void proceed(RGBImage origin, List<Args.Job> jobs) throws IOException {
        RGBImage prev = origin;
        for (final Args.Job job : jobs) {
            final RGBImage blurred = prev
                    .blurred(job.getRadius())
                    .write(Format.PNG, job.getBlurredPath());
            origin
                    .enhanced(blurred)
                    .write(Format.PNG, job.getEnhancedPath());
            prev = blurred;
        }
    }
}
