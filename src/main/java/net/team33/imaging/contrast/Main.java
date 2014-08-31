package net.team33.imaging.contrast;

import net.team33.imaging.Format;
import net.team33.imaging.RGBImage;

import java.io.IOException;

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
        RGBImage.read(args.getSourcePath())
                .enhanced(args.getRadius(), args.getFactor())
                .write(Format.PNG, args.getDestinationPath());
    }
}
