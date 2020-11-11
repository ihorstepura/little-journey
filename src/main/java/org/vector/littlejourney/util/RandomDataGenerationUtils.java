package org.vector.littlejourney.util;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomDataGenerationUtils {

    private static final Random random = new Random();

    public static <E> E getRandomElement(List<E> elements) {

        int index = random.nextInt(elements.size());

        return elements.get(index);
    }

    public static double getRandomDouble(int from, int to) {

        return from + (Math.random() * to);
    }

    public static Date getRandomDate() {

        long time = System.currentTimeMillis() + random.nextInt();

        return new Date(time);
    }
}
