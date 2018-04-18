package com.meteogroup.util.compression;

import java.util.List;

public class PointCompression {

    private static final String CHARACTER_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
    private static final int ACCURACY = 100000;

    public static String compress(List<Point> points) {
        class P {
            private long y, x;

            private void set(long y, long x) {
                this.y = y;
                this.x = x;
            }
        }

        final P bs = new P(), np = new P(), d = new P();
        final StringBuilder rs = new StringBuilder();

        for (Point point : points) {
            np.set(Math.round(point.getLatitude() * ACCURACY), Math.round(point.getLongitude() * ACCURACY));    // step 2

            d.set(np.y - bs.y, np.x - bs.x);    // step 3
            d.set((d.y << 1) ^ (d.y >> 31), (d.x << 1) ^ (d.x >> 31));  // step 4 and 5
            long idx = ((d.y + d.x) * (d.y + d.x + 1) / 2) + d.y;   // step 6

            while (idx > 0) {
                int rem = (int) (idx & 31);  // step 7
                idx = (idx - rem) / 32;
                rs.append(CHARACTER_TABLE.charAt(idx > 0 ? rem + 32 : rem));    // step 8 and 9
            }

            bs.set(np.y, np.x);
        }

        return rs.toString();   // step 10
    }

    public static List<Point> decompress(String compressed) {
        return null;
    }

}
