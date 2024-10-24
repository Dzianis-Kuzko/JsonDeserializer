package ru.clevertec.json_deserializer.test_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMapFactory {
    public static Map<String, Object> createMapForJsonWithArrayAndObject(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Denis");
        map.put("age", 34);
        map.put("is_active", true);
        Map<String, Object> adressMap = new HashMap<>();
        adressMap.put("city", "Minsk");
        adressMap.put("street", "Mira");
        adressMap.put("house_number", "158");
        map.put("address", adressMap);
        List<String> phones = new ArrayList<>();
        phones.add("+375336951582");
        phones.add("+375331234567");
        map.put("phones", phones);
        return map;
    }
}
