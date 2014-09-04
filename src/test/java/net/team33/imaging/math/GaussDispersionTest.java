package net.team33.imaging.math;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GaussDispersionTest {

    private static List<Double> toList(final GaussDispersion subject) {
        return toList(subject, subject.getEffectiveRadius());
    }

    private static List<Double> toList(final GaussDispersion subject, final int radius) {
        return toList(subject, -radius, radius);
    }

    private static List<Double> toList(final GaussDispersion subject, final int fromDistance, final int toDistance) {
        final List<Double> result = new ArrayList<>((1 + toDistance) - fromDistance);
        for (int distance = fromDistance; distance <= toDistance; ++distance) {
            result.add(subject.getWeight(distance));
        }
        return result;
    }

    @Ignore
    @Test
    public final void testGetWeight___0() {
        assertEquals(
                Arrays.asList(0, 0, 1, 0, 0),
                toList(GaussDispersion.forRadius(0))
        );
    }

    @Test
    public final void testGetWeight___1() {
        assertEquals(
                Arrays.asList(0, 1, 2, 1, 0),
                toList(GaussDispersion.forRadius(1))
        );
    }

    @Test
    public final void testGetWeight___2() {
        assertEquals(
                Arrays.asList(1, 4, 6, 4, 1),
                toList(GaussDispersion.forRadius(2))
        );
    }

    @Test
    public final void testGetWeight___3() {
        assertEquals(
                Arrays.asList(1, 6, 15, 20, 15, 6, 1),
                toList(new GaussDispersion(1.3185, 20, .99))
        );
    }

    @Test
    public final void testGetWeight___4() {
        assertEquals(
                Arrays.asList(1, 8, 28, 56, 70, 56, 28, 8, 1),
                toList(new GaussDispersion(1.5, 70, .99))
        );
    }

    @Test
    public final void testGetWeight___8() {
        assertEquals(
                Arrays.asList(
                        0, 0, 0, 0, 0, 0, 1, 16, 120, 560, 1820, 4368, 8008, 11440,
                        12870,
                        11440, 8008, 4368, 1820, 560, 120, 16, 1, 0, 0, 0, 0, 0, 0
                ),
                toList(new GaussDispersion(2.061, 12870, .99999), 14)
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__12() {
        final GaussDispersion subject = GaussDispersion.forRadius(12);
        assertEquals(
                Arrays.asList(
                        0, 0, 1, 24,
                        276, 2024, 10626, 42504, 134596, 346104, 735471, 1307504, 1961256, 2496144,
                        2704156,
                        2496144, 1961256, 1307504, 735471, 346104, 134596, 42504, 10626, 2024, 276,
                        24, 1, 0, 0
                ),
                toList(subject, 14)
        );
        assertEquals(
                subject.getNominalRadius(),
                subject.getEffectiveRadius()
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__13() {
        final GaussDispersion subject = GaussDispersion.forRadius(13);
        assertEquals(
                Arrays.asList(
                        0, 1, 26, 325,
                        2600, 14950, 65780, 230230, 657800, 1562275, 3124550, 5311735, 7726160, 9657700,
                        10400600,
                        9657700, 7726160, 5311735, 3124550, 1562275, 657800, 230230, 65780, 14950, 2600,
                        325, 26, 1, 0
                ),
                toList(subject, 14)
        );
        assertEquals(
                subject.getNominalRadius(),
                subject.getEffectiveRadius()
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__14() {
        final GaussDispersion subject = GaussDispersion.forRadius(14);
        assertEquals(
                Arrays.asList(
                        0, 7, 94, 819,
                        5118, 24570, 94185, 296010, 777026, 1726725, 3280777, 5368545, 7605438, 9360540,
                        10029150,
                        9360540, 7605438, 5368545, 3280777, 1726725, 777026, 296010, 94185, 24570, 5118,
                        819, 94, 7, 0
                ),
                toList(subject, 14)
        );
        assertEquals(
                13,
                subject.getEffectiveRadius()
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__15() {
        final GaussDispersion subject = GaussDispersion.forRadius(15);
        assertEquals(
                Arrays.asList(
                        0, 1, 27, 253, 1712,
                        8906, 37110, 127237, 365807, 894196, 1877813, 3414206, 5405826, 7484990, 9088917,
                        9694845,
                        9088917, 7484990, 5405826, 3414206, 1877813, 894196, 365807, 127237, 37110, 8906,
                        1712, 253, 27, 1, 0
                ),
                toList(subject, 15)
        );
        assertEquals(
                14,
                subject.getEffectiveRadius()
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__16() {
        final GaussDispersion subject = GaussDispersion.forRadius(16);
        assertEquals(
                Arrays.asList(
                        0, 7, 77, 561, 3146,
                        14159, 52591, 164348, 438262, 1008003, 2016007, 3528013, 5427712, 7366181, 8839417,
                        9391881,
                        8839417, 7366181, 5427712, 3528013, 2016007, 1008003, 438262, 164348, 52591, 14159,
                        3146, 561, 77, 7, 0
                ),
                toList(subject, 15)
        );
        assertEquals(
                14,
                subject.getEffectiveRadius()
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__17() {
        final GaussDispersion subject = GaussDispersion.forRadius(17);
        assertEquals(
                Arrays.asList(
                        0, 2, 23, 181, 1086, 5253,
                        21014, 70922, 204887, 512219, 1117569, 2142007, 3624936, 5437404, 7249873, 8609224,
                        9115649,
                        8609224, 7249873, 5437404, 3624936, 2142007, 1117569, 512219, 204887, 70922, 21014,
                        5253, 1086, 181, 23, 2, 0
                ),
                toList(subject, 16)
        );
        assertEquals(
                15,
                subject.getEffectiveRadius()
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__30() {
        final GaussDispersion subject = GaussDispersion.forRadius(30);
        assertEquals(
                Arrays.asList(
                        0, 1,
                        8, 39, 162, 601, 2019, 6192, 17416, 45078, 107687, 238046,
                        487994, 929514, 1647775, 2722410, 4197050, 6043752, 8135820, 10245107, 12074590, 13323686,
                        13767809,
                        13323686, 12074590, 10245107, 8135820, 6043752, 4197050, 2722410, 1647775, 929514, 487994,
                        238046, 107687, 45078, 17416, 6192, 2019, 601, 162, 39, 8,
                        1, 0
                ),
                toList(subject, 22)
        );
        assertEquals(
                21,
                subject.getEffectiveRadius()
        );
    }

    @Test
    public final void testGetWeightAndEffectiveRadius__60() {
        final GaussDispersion subject = GaussDispersion.forRadius(60);
        assertEquals(
                Arrays.asList(
                        1, 4, 13, 36, 94, 232, 547, 1243, 2716, 5712,
                        11567, 22570, 42453, 77009, 134765, 227604, 371094, 584275, 888586, 1305677,
                        1854062, 2544791, 3376742, 4332424, 5375415, 6450498, 7487185, 8406664, 9131377, 9595684,
                        9755612,
                        9595684, 9131377, 8406664, 7487185, 6450498, 5375415, 4332424, 3376742, 2544791, 1854062,
                        1305677, 888586, 584275, 371094, 227604, 134765, 77009, 42453, 22570, 11567,
                        5712, 2716, 1243, 547, 232, 94, 36, 13, 4, 1
                ),
                toList(subject, 30)
        );
        assertEquals(
                30,
                subject.getEffectiveRadius()
        );
    }

    @Ignore
    @Test
    public final void testGetWeightAndEffectiveRadius_300() {
        final GaussDispersion subject = GaussDispersion.forRadius(300);
        assertEquals(
                Arrays.asList(
                        0, 0, 0, 1, 2, 3, 6, 9, 14, 22,
                        33, 50, 75, 111, 164, 241, 350, 506, 725, 1032,
                        1459, 2049, 2857, 3957, 5443, 7436, 10089, 13597, 18200, 24196,
                        31950, 41904, 54588, 70631, 90774, 115874, 146920, 185031, 231462, 287600, 354956,
                        435150, 529888, 640931, 770056, 919008, 1089443, 1282858, 1500528, 1743419, 2012118,
                        2306749, 2626903, 2971568, 3339077, 3727068, 4132468, 4551495, 4979684, 5411949, 5842657,
                        6265746, 6674850, 7063455, 7425065, 7753384, 8042493, 8287029, 8482346, 8624667, 8711202,
                        8740240,
                        8711202, 8624667, 8482346, 8287029, 8042493, 7753384, 7425065, 7063455, 6674850, 6265746,
                        5842657, 5411949, 4979684, 4551495, 4132468, 3727068, 3339077, 2971568, 2626903, 2306749,
                        2012118, 1743419, 1500528, 1282858, 1089443, 919008, 770056, 640931, 529888, 435150,
                        354956, 287600, 231462, 185031, 146920, 115874, 90774, 70631, 54588, 41904, 31950,
                        24196, 18200, 13597, 10089, 7436, 5443, 3957, 2857, 2049, 1459,
                        1032, 725, 506, 350, 241, 164, 111, 75, 50, 33,
                        22, 14, 9, 6, 3, 2, 1, 0, 0, 0
                ),
                toList(subject, 71)
        );
        assertEquals(
                68,
                subject.getEffectiveRadius()
        );
    }

    @Ignore
    @Test
    public final void testGetWeightAndEffectiveRadius_999() {
        final GaussDispersion subject = GaussDispersion.forRadius(999);
        assertEquals(
                Arrays.asList(
                        0, 0, 0, 0, 1, 1, 1, 2, 3, 4,
                        5, 6, 8, 10, 13, 16, 21, 26, 33, 41,
                        51, 64, 80, 99, 123, 152, 187, 230, 283, 347,
                        425, 519, 633, 770, 935, 1132, 1369, 1651, 1988, 2389,
                        2865, 3428, 4094, 4880, 5805, 6891, 8164, 9652, 11389, 13412,
                        15761, 18485, 21637, 25274, 29464, 34279, 39800, 46119, 53333, 61551,
                        70893, 81489, 93480, 107021, 122277, 139427, 158664, 180192, 204231, 231012,
                        260780, 293794, 330323, 370649, 415064, 463869, 517374, 575893, 639746, 709254,
                        784738, 866516, 954899, 1050188, 1152673, 1262624, 1380292, 1505905, 1639658, 1781716,
                        1932206, 2091210, 2258768, 2434867, 2619442, 2812368, 3013459, 3222467, 3439076, 3662900,
                        3893485, 4130305, 4372764, 4620194, 4871860, 5126957, 5384619, 5643920, 5903875, 6163453,
                        6421575, 6677127, 6928966, 7175925, 7416826, 7650486, 7875729, 8091393, 8296342, 8489475,
                        8669737, 8836126, 8987704, 9123607, 9243050, 9345338, 9429868, 9496142, 9543766, 9572455,
                        9582037,
                        9572455, 9543766, 9496142, 9429868, 9345338, 9243050, 9123607, 8987704, 8836126, 8669737,
                        8489475, 8296342, 8091393, 7875729, 7650486, 7416826, 7175925, 6928966, 6677127, 6421575,
                        6163453, 5903875, 5643920, 5384619, 5126957, 4871860, 4620194, 4372764, 4130305, 3893485,
                        3662900, 3439076, 3222467, 3013459, 2812368, 2619442, 2434867, 2258768, 2091210, 1932206,
                        1781716, 1639658, 1505905, 1380292, 1262624, 1152673, 1050188, 954899, 866516, 784738,
                        709254, 639746, 575893, 517374, 463869, 415064, 370649, 330323, 293794, 260780,
                        231012, 204231, 180192, 158664, 139427, 122277, 107021, 93480, 81489, 70893,
                        61551, 53333, 46119, 39800, 34279, 29464, 25274, 21637, 18485, 15761,
                        13412, 11389, 9652, 8164, 6891, 5805, 4880, 4094, 3428, 2865,
                        2389, 1988, 1651, 1369, 1132, 935, 770, 633, 519, 425,
                        347, 283, 230, 187, 152, 123, 99, 80, 64, 51,
                        41, 33, 26, 21, 16, 13, 10, 8, 6, 5,
                        4, 3, 2, 1, 1, 1, 0, 0, 0, 0
                ),
                toList(subject, 130)
        );
        assertEquals(
                126,
                subject.getEffectiveRadius()
        );
    }
}
