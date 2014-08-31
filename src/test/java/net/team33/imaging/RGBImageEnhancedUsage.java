package net.team33.imaging;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("JUnitTestMethodWithNoAssertions")
public class RGBImageEnhancedUsage {

    @Test
    public final void useEnhanced() throws IOException {
        final Path cwd
                = Paths.get("C:\\Users\\andi\\Pictures\\Alben\\Erinnerungen\\2014\\Lechweg\\Tag02\\Peng\\DSC_2963\\");
        RGBImage.read(cwd.resolve("DSC_2963.png"))
                .enhanced(500, 0.2)
                .write(Format.PNG, cwd.resolve("DSC_2963.500.020.png"));
    }
}
