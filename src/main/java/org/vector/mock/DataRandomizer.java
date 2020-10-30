package org.vector.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataRandomizer {

    public static String randomStationName() {

        List<String> names = new ArrayList<>(150);

        names.add("Киев");
        names.add("Харьков");
        names.add("Одесса");
        names.add("Днепр");
        names.add("Львов");
        names.add("Ужгород");
        names.add("Каменское");
        names.add("Верховцево");
        names.add("Пятихатки");
        names.add("Бердянск");
        names.add("Донецк");
        names.add("Мариуполь");
        names.add("Мелитополь");
        names.add("Кривой Рог");
        names.add("Кременчуг");
        names.add("Павлоград");
        names.add("Полтава");
        names.add("Хмельницкий");
        names.add("Луцк");
        names.add("Никополь");
        names.add("Николаев");
        names.add("Ровно");

        Random random = new Random();

        int index = random.nextInt(names.size());

        return names.get(index);
    }

    public static double randomTicketCost() {

        return 20 + (Math.random() * 1000);
    }

    public static Date randomDuration() {

        Random random = new Random();

        long time = System.currentTimeMillis() + random.nextInt();

        return new Date(time);
    }
}
