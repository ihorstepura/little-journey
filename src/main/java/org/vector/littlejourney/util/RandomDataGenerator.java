package org.vector.littlejourney.util;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomDataGenerator {

    private static final Random random = new Random();

    public static <E> E randomElement(List<E> elements) {

        int index = random.nextInt(elements.size());

        return elements.get(index);
    }

    public static double randomDouble(int from, int to) {

        return from + (Math.random() * to);
    }

    public static Date randomDate() {

        long time = System.currentTimeMillis() + random.nextInt();

        return new Date(time);
    }
}
