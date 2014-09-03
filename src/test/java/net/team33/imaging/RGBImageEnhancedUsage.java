package net.team33.imaging;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("JUnitTestMethodWithNoAssertions")
public class RGBImageEnhancedUsage {

    private static final Path CWD;
    private static final String PNG;
    private static final String FILE_NAME;
    private static final String ORIGINAL_NAME;

    static {
        PNG = ".png";
        FILE_NAME = "DSC_2963";
        CWD = Paths.get("C:\\Users\\andi\\Pictures\\Alben\\Erinnerungen\\2014\\Lechweg\\Tag02\\Peng\\DSC_2963\\");
        ORIGINAL_NAME = FILE_NAME + "_0" + PNG;
    }

    private static void blur(final int radius) throws IOException {
        RGBImage.read(CWD.resolve(ORIGINAL_NAME))
                .blurred(radius)
                .write(Format.PNG, CWD.resolve(String.format("blurred(%04d)%s", radius, PNG)));
    }

    private static void enhance(final int radius, final double intensity) throws IOException {
        RGBImage.read(CWD.resolve(ORIGINAL_NAME))
                .enhanced(radius, intensity)
                .write(Format.PNG, CWD.resolve(String.format("enhanced(%04d, %s)%s", radius, intensity, PNG)));
    }

    @Ignore
    @Test
    public final void enhance__500_020() throws IOException {
        enhance(500, 0.2);
    }

    @Ignore
    @Test
    public final void enhance_xxxx_050() throws IOException {
        final int[] radien = {20, 40, 80, 160, 320, 640, 1280, 2560, 5120};
        for (int radius : radien) {
            enhance(radius, 0.5);
        }
    }

    //@Ignore
    @Test
    public final void enhance_5000_xxx() throws IOException {
        final double[] intensities = {0.5, 0.4, 0.3, 0.2, 0.1};
        for (double intensity : intensities) {
            enhance(5000, intensity);
        }
    }

    @Ignore
    @Test
    public final void blur_xxxx() throws IOException {
        final int[] radien = {100, 200, 400, 700, 1000, 2000};
        for (int radius : radien) {
            blur(radius);
        }
    }
}
