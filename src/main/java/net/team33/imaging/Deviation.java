package net.team33.imaging;

public class Deviation {

    public static final int MIN_RADIUS = 0;
    public static final int MAX_RADIUS = 16;
    private static final Deviation[][] VALUES = {
            {
                    new Deviation(0, 1)
            },
            {
                    new Deviation(1, 1),
                    new Deviation(0, 2),
                    new Deviation(-1, 1)
            },
            {
                    new Deviation(2, 1), new Deviation(1, 4),
                    new Deviation(0, 6),
                    new Deviation(-1, 4), new Deviation(-2, 1)
            },
            {
                    new Deviation(3, 1), new Deviation(2, 6), new Deviation(1, 15),
                    new Deviation(0, 20),
                    new Deviation(-1, 15), new Deviation(-2, 6), new Deviation(-3, 1)
            },
            {
                    new Deviation(4, 1), new Deviation(3, 8), new Deviation(2, 28), new Deviation(1, 56),
                    new Deviation(0, 70),
                    new Deviation(-1, 56), new Deviation(-2, 28), new Deviation(-3, 8), new Deviation(-4, 1)
            },
            {
                    new Deviation(5, 1), new Deviation(4, 10),
                    new Deviation(3, 45), new Deviation(2, 120), new Deviation(1, 210),
                    new Deviation(0, 252),
                    new Deviation(-1, 210), new Deviation(-2, 120), new Deviation(-3, 45),
                    new Deviation(-4, 10), new Deviation(-5, 1)
            },
            {
                    new Deviation(6, 1), new Deviation(5, 12), new Deviation(4, 66),
                    new Deviation(3, 220), new Deviation(2, 495), new Deviation(1, 792),
                    new Deviation(0, 924),
                    new Deviation(-1, 792), new Deviation(-2, 495), new Deviation(-3, 220),
                    new Deviation(-4, 66), new Deviation(-5, 12), new Deviation(-6, 1)
            },
            {
                    new Deviation(7, 1), new Deviation(6, 14), new Deviation(5, 91),
                    new Deviation(4, 364), new Deviation(3, 1001), new Deviation(2, 2002), new Deviation(1, 3003),
                    new Deviation(0, 3432),
                    new Deviation(-1, 3003), new Deviation(-2, 2002), new Deviation(-3, 1001), new Deviation(-4, 364),
                    new Deviation(-5, 91), new Deviation(-6, 14), new Deviation(-7, 1)
            },
            {
                    new Deviation(8, 1), new Deviation(7, 16), new Deviation(6, 120), new Deviation(5, 560),
                    new Deviation(4, 1820), new Deviation(3, 4368), new Deviation(2, 8008), new Deviation(1, 11440),
                    new Deviation(0, 12870),
                    new Deviation(-1, 11440), new Deviation(-2, 8008), new Deviation(-3, 4368), new Deviation(-4, 1820),
                    new Deviation(-5, 560), new Deviation(-6, 120), new Deviation(-7, 16), new Deviation(-8, 1)
            },
            {
                    new Deviation(9, 1), new Deviation(8, 18), new Deviation(7, 153),
                    new Deviation(6, 816), new Deviation(5, 3060), new Deviation(4, 8568),
                    new Deviation(3, 18564), new Deviation(2, 31824), new Deviation(1, 43758),
                    new Deviation(0, 48620),
                    new Deviation(-1, 43758), new Deviation(-2, 31824), new Deviation(-3, 18564),
                    new Deviation(-4, 8568), new Deviation(-5, 3060), new Deviation(-6, 816),
                    new Deviation(-7, 153), new Deviation(-8, 18), new Deviation(-9, 1)
            },
            {
                    new Deviation(10, 1), new Deviation(9, 20), new Deviation(8, 190), new Deviation(7, 1140),
                    new Deviation(6, 4845), new Deviation(5, 15504), new Deviation(4, 38760),
                    new Deviation(3, 77520), new Deviation(2, 125970), new Deviation(1, 167960),
                    new Deviation(0, 184756),
                    new Deviation(-1, 167960), new Deviation(-2, 125970), new Deviation(-3, 77520),
                    new Deviation(-4, 38760), new Deviation(-5, 15504), new Deviation(-6, 4845),
                    new Deviation(-7, 1140), new Deviation(-8, 190), new Deviation(-9, 20), new Deviation(-10, 1)
            },
            {
                    new Deviation(11, 1), new Deviation(10, 22),
                    new Deviation(9, 231), new Deviation(8, 1540), new Deviation(7, 7315),
                    new Deviation(6, 26334), new Deviation(5, 74613), new Deviation(4, 170544),
                    new Deviation(3, 319770), new Deviation(2, 497420), new Deviation(1, 646646),
                    new Deviation(0, 705432),
                    new Deviation(-1, 646646), new Deviation(-2, 497420), new Deviation(-3, 319770),
                    new Deviation(-4, 170544), new Deviation(-5, 74613), new Deviation(-6, 26334),
                    new Deviation(-7, 7315), new Deviation(-8, 1540), new Deviation(-9, 231),
                    new Deviation(-10, 22), new Deviation(-11, 1)
            },
            {
                    new Deviation(12, 1), new Deviation(11, 24), new Deviation(10, 276),
                    new Deviation(9, 2024), new Deviation(8, 10626), new Deviation(7, 42504),
                    new Deviation(6, 134596), new Deviation(5, 346104), new Deviation(4, 735471),
                    new Deviation(3, 1307504), new Deviation(2, 1961256), new Deviation(1, 2496144),
                    new Deviation(0, 2704156),
                    new Deviation(-1, 2496144), new Deviation(-2, 1961256), new Deviation(-3, 1307504),
                    new Deviation(-4, 735471), new Deviation(-5, 346104), new Deviation(-6, 134596),
                    new Deviation(-7, 42504), new Deviation(-8, 10626), new Deviation(-9, 2024),
                    new Deviation(-10, 276), new Deviation(-11, 24), new Deviation(-12, 1)
            },
            {
                    new Deviation(13, 1),
                    new Deviation(12, 26), new Deviation(11, 325), new Deviation(10, 2600),
                    new Deviation(9, 14950), new Deviation(8, 65780), new Deviation(7, 230230),
                    new Deviation(6, 657800), new Deviation(5, 1562275), new Deviation(4, 3124550),
                    new Deviation(3, 5311735), new Deviation(2, 7726160), new Deviation(1, 9657700),
                    new Deviation(0, 10400600),
                    new Deviation(-1, 9657700), new Deviation(-2, 7726160), new Deviation(-3, 5311735),
                    new Deviation(-4, 3124550), new Deviation(-5, 1562275), new Deviation(-6, 657800),
                    new Deviation(-7, 230230), new Deviation(-8, 65780), new Deviation(-9, 14950),
                    new Deviation(-10, 2600), new Deviation(-11, 325), new Deviation(-12, 26),
                    new Deviation(-13, 1)
            },
            {
                    new Deviation(14, 1), new Deviation(13, 28),
                    new Deviation(12, 378), new Deviation(11, 3276), new Deviation(10, 20475),
                    new Deviation(9, 98280), new Deviation(8, 376740), new Deviation(7, 1184040),
                    new Deviation(6, 3108105), new Deviation(5, 6906900), new Deviation(4, 13123110),
                    new Deviation(3, 21474180), new Deviation(2, 30421755), new Deviation(1, 37442160),
                    new Deviation(0, 40116600),
                    new Deviation(-1, 37442160), new Deviation(-2, 30421755), new Deviation(-3, 21474180),
                    new Deviation(-4, 13123110), new Deviation(-5, 6906900), new Deviation(-6, 3108105),
                    new Deviation(-7, 1184040), new Deviation(-8, 376740), new Deviation(-9, 98280),
                    new Deviation(-10, 20475), new Deviation(-11, 3276), new Deviation(-12, 378),
                    new Deviation(-13, 28), new Deviation(-14, 1)
            },
            {
                    new Deviation(15, 1), new Deviation(14, 30), new Deviation(13, 435),
                    new Deviation(12, 4060), new Deviation(11, 27405), new Deviation(10, 142506),
                    new Deviation(9, 593775), new Deviation(8, 2035800), new Deviation(7, 5852925),
                    new Deviation(6, 14307150), new Deviation(5, 30045015), new Deviation(4, 54627300),
                    new Deviation(3, 86493225), new Deviation(2, 119759850), new Deviation(1, 145422675),
                    new Deviation(0, 155117520),
                    new Deviation(-1, 145422675), new Deviation(-2, 119759850), new Deviation(-3, 86493225),
                    new Deviation(-4, 54627300), new Deviation(-5, 30045015), new Deviation(-6, 14307150),
                    new Deviation(-7, 5852925), new Deviation(-8, 2035800), new Deviation(-9, 593775),
                    new Deviation(-10, 142506), new Deviation(-11, 27405), new Deviation(-12, 4060),
                    new Deviation(-13, 435), new Deviation(-14, 30), new Deviation(-15, 1)
            },
            {
                    new Deviation(16, 1),
                    new Deviation(15, 32), new Deviation(14, 496), new Deviation(13, 4960),
                    new Deviation(12, 35960), new Deviation(11, 201376), new Deviation(10, 906192),
                    new Deviation(9, 3365856), new Deviation(8, 10518300), new Deviation(7, 28048800),
                    new Deviation(6, 64512240), new Deviation(5, 129024480), new Deviation(4, 225792840),
                    new Deviation(3, 347373600), new Deviation(2, 471435600), new Deviation(1, 565722720),
                    new Deviation(0, 601080390),
                    new Deviation(-1, 565722720), new Deviation(-2, 471435600), new Deviation(-3, 347373600),
                    new Deviation(-4, 225792840), new Deviation(-5, 129024480), new Deviation(-6, 64512240),
                    new Deviation(-7, 28048800), new Deviation(-8, 10518300), new Deviation(-9, 3365856),
                    new Deviation(-10, 906192), new Deviation(-11, 201376), new Deviation(-12, 35960),
                    new Deviation(-13, 4960), new Deviation(-14, 496), new Deviation(-15, 32),
                    new Deviation(-16, 1)
            }
    };

    private final int delta;
    private final int weight;

    public Deviation(final int delta, final int weight) {
        this.delta = delta;
        this.weight = weight;
    }

    public static Deviation[] values(final int radius) {
        return VALUES[radius];
    }

    public final int getDelta() {
        return delta;
    }

    public final long getWeight() {
        return weight;
    }
}
