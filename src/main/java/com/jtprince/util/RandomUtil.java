package com.jtprince.util;

import java.util.Random;

public final class RandomUtil {

    private static final Random RANDOM;

    static {
        RANDOM = new Random();
    }

    private RandomUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static int nextInt(int origin, int bound) {
        return RANDOM.nextInt(origin, bound);
    }
}
