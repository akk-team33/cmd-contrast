package net.team33.imaging.contrast;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 73948 (14:18)
// 327272 (14:01)

public final class Main {

    private Main() {
    }

    public static void main(final String[] args){
        try {
            proceed(Args.build(args));
        } catch (Args.Problem caught) {
            caught.printStackTrace();
        } catch (IOException caught) {
            caught.printStackTrace();
        }
    }

    private static void proceed(final Args args) throws IOException {
        System.out.println(args);
        BufferedImage image = ImageIO.read(args.getSourcePath().toFile());
        Result result = proceed(args, image, image.getWidth(), image.getHeight());
        ImageIO.write(result.getMinimum(), "png", args.getMinimumPath().toFile());
        ImageIO.write(result.getMedium(), "png", args.getMediumPath().toFile());
        ImageIO.write(result.getMaximum(), "png", args.getMaximumPath().toFile());
    }

    private static Result proceed(final Args args, final BufferedImage image, final int width, final int height) {
        System.out.println("width:  " + width);
        System.out.println("height: " + height);
        final Result result = new Result(image, width, height);
        for (int y = 0; y < height; ++y) {
            System.out.println(y);
            proceed(args, image, y, width, height, result);
        }
        return result;
    }

    private static void proceed(
            final Args args, final BufferedImage image, final int y,
            final int width, final int height, final Result result) {

        for (int x = 0; x < width; ++x) {
            //System.out.print('.');
            proceed(args, image, x, y, width, height, result);
        }
    }

    private static void proceed(
            final Args args, final BufferedImage image, final int x, final int y,
            final int width, final int height, final Result result) {
        // final int rgb = image.getRGB(x, y);
        // System.out.printf("x(%d), y(%d): rgb(%08x)%n", x, y, rgb);
        final PixelInfo pixelInfo = PixelInfo.valueOf(args, image, x, y, width, height);
        result.getMinimum().setRGB(x, y, pixelInfo.getMinimum());
        result.getMaximum().setRGB(x, y, pixelInfo.getMaximum());
        result.getMedium().setRGB(x, y, pixelInfo.getMedium());
    }
}
