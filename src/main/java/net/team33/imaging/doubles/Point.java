package net.team33.imaging.doubles;

import static java.util.Objects.requireNonNull;

/**
 * Represents a point within a two dimensional coordinate system.
 */
public class Point {

    private final double x;
    private final double y;

    public Point(final double x, final double y) {
        this.x = requireNonNull(x);
        this.y = requireNonNull(y);
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }

    @Override
    public final String toString() {
        return String.format("(%s, %s)", x, y);
    }

    @Override
    public final boolean equals(final Object other) {
        return (this == other) || ((other instanceof Point) && equals((Point) other));
    }

    private boolean equals(final Point other) {
        return x == other.x && y == other.y;
    }

    @Override
    public final int hashCode() {
        return (31 * Double.hashCode(x)) + Double.hashCode(y);
    }
}
