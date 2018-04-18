package com.meteogroup.util.compression;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PointCompressionTest {

    @DataProvider
    public Object[][] samples() {
        return new Object[][]{
            sample("vx1vilihnM6hR7mEl2Q",
                point(35.894309002906084, -110.72522000409663),
                point(35.893930979073048, -110.72577999904752),
                point(35.893744984641671, -110.72606003843248),
                point(35.893366960808635, -110.72661500424147)),
            sample("s9t5rilgvC6y0rgktqD",
                point(52.1161994934082, 13.787839889526367),
                point(39.94520950317383, 15.137619972229004)),
            sample("j7-wuki2qLh17tv3phlG",
                point(83.45456, 57.75121),
                point(12.77555, 91.15116))
        };
    }

    @Test(dataProvider = "samples")
    public void testCompress(String expected, List<Point> points) {
        String actual = PointCompression.compress(points);

        Assert.assertNotNull(actual);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "samples")
    public void testDecompress(String compressed, List<Point> expected) {
        List<Point> actual = PointCompression.decompress(compressed);

        Assert.assertNotNull(actual);
        Assert.assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertNotNull(actual.get(i));
            Assert.assertEquals(actual.get(i).getLatitude(), round(expected.get(i).getLatitude()));
            Assert.assertEquals(actual.get(i).getLongitude(), round(expected.get(i).getLongitude()));
        }
    }

    private static double round(double v) {
        return Math.round(v * 100000) / 100000.0;
    }

    private static Object[] sample(String compressed, Point... points) {
        return new Object[]{compressed, Arrays.asList(points)};
    }

    private static Point point(double latitude, double longitude) {
        return new Point(latitude, longitude);
    }
}