package net.team33.imaging;

public enum Format {
    PNG("png");

    private final String rawName;

    Format(final String rawName) {
        this.rawName = rawName;
    }

    public final String getRawName() {
        return rawName;
    }
}
