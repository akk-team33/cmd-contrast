package net.team33.imaging.contrast.doubles;

/**
 * Represents a straight line within a two dimensional coordinate system.
 */
public class StraightLine {

    private final double m;
    private final double b;

    public StraightLine(final double m, final double b) {
        this.m = m;
        this.b = b;
    }

    public final double getM() {
        return m;
    }

    public final double getB() {
        return b;
    }

    public final double y(final double x) {
        return (m * x) + b;
    }

    public final double x(final double y) {
        return (y - b) / m;
    }

    public final Point intercept(final StraightLine other) {
        return point((other.b - b) / (m - other.m));
    }

    private Point point(final double x) {
        return new Point(x, y(x));
    }

    @Override
    public final String toString() {
        return String.format("StraightLine{%s * x + %s}", m, b);
    }

    public final double absLength(final double x1, final double x2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y(x2) - y(x1), 2));
    }

    public final Function function() {
        return this::y;
    }
}
