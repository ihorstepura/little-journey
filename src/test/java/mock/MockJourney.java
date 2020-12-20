package mock;

import java.util.ArrayList;
import java.util.List;

public class MockJourney {

    private final List<String> names;

    public MockJourney() {

        names = new ArrayList<>(50);

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
    }

    public List<String> getCity() {

        return names;
    }
}
