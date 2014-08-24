package net.team33.imaging.contrast;

public class Mapping {

    private static final int LIMIT = 255;

    public static double apply(final double original, final double medium, final double spread) {
        if (original == medium || LIMIT <= spread) {
            return original;
        } else if (original < medium) {
            final Point center = new Point(0, 0);
            return original;
        } else {
            return 0;
        }
    }

    private static class Point {
        final double x;
        final double y;

        private Point(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }
}
