package net.team33.imaging.contrast.doubles;

import net.team33.imaging.contrast.Rgb;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import static java.lang.Math.abs;

public class Graph {

    private final BufferedImage image;

    public Graph(final int xMin, final int xMax, final int yMin, final int yMax, final int padding,
                 final Function function) {

        final int width = (xMax - xMin) + (2 * padding);
        final int height = (yMax - yMin) + (2 * padding);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int yImage = 0; yImage < image.getHeight(); ++yImage) {
            for (int xImage = 0; xImage < image.getWidth(); ++xImage) {
                final int xGraph = xImage + xMin - padding;
                final int yGraph = yMax - yImage + padding;
                final double yFunction = function.apply(xGraph);
                final int rgb;
                if (0.5 >= abs(yFunction - yGraph)) {
                    rgb = Rgb.combination(0, 0, 0);
                } else if ((0 == xGraph) || (0 == yGraph)) {
                    rgb = Rgb.combination(0, 0, 255);
                } else if ((0 == (xGraph % 16)) || (0 == (yGraph % 16))) {
                    rgb = Rgb.combination(128, 128, 255);
                } else if ((0 == (xGraph % 8)) || (0 == (yGraph % 8))) {
                    rgb = Rgb.combination(192, 192, 255);
                } else {
                    rgb = -1;
                }

                image.setRGB(xImage, yImage, rgb);
            }
        }

        line(xMin - padding, 0, xMax + padding, 0, Rgb.combination(0, 0, 255));
    }

    public Graph(final Function function) {
        this(0, 255, 0, 255, 8, function);
    }

    private void line(final double x1, final double y1, final double x2, final double y2, final int rgb) {
    }

    public void write(final Path path, final String name, final String format) throws IOException {
        ImageIO.write(image, format, path.resolve(String.format("%s.%s", name, format)).toFile());
    }
}
