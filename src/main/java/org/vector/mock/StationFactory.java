package org.vector.mock;

import org.vector.Station;

import java.util.*;

public class StationFactory {

    public List<Station> generateStations(int number) {
        return generateStation(number);
    }

    private List<Station> generateStation(int number) {

        List<Station> stations = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {
            stations.add(new Station(randomStationName()));
        }

        return stations;
    }

    private String randomStationName() {

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

        Random random = new Random();
        int index = random.nextInt(names.size());

        return names.get(index);
    }
}
