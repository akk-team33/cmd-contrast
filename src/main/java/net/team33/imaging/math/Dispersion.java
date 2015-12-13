package net.team33.imaging.math;

public interface Dispersion {

    int getMinDistance();

    int getNominalRadius();

    int getEffectiveRadius();

    int getWeight(int distance);
}
