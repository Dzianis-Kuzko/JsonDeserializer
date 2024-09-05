package ru.clevertec.json_deserializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {
    private JsonParser jsonParser;

    @BeforeEach
    void setUp() {
        jsonParser = new JsonParser();
    }

    @Test
    void parseJsonWithArrayAndObjectToMapSuccess() {
        String json = """
                {
                    "name": "Denis",
                    "age": 34,
                    "isActive": true,
                    "address": {
                        "city": "Minsk",
                        "street": "Mira",
                        "houseNumber": "158"
                    },
                    "phones": ["+375336951582", "+375331234567"]
                }
                """;

        Map<String, Object> expectedMap = new HashMap<>();

        expectedMap.put("name", "Denis");
        expectedMap.put("age", 34);
        expectedMap.put("isActive", true);
        Map<String, Object> adressMap = new HashMap<>();
        adressMap.put("city", "Minsk");
        adressMap.put("street", "Mira");
        adressMap.put("houseNumber", "158");
        expectedMap.put("address", adressMap);
        List<String> phones = new ArrayList<>();
        phones.add("+375336951582");
        phones.add("+375331234567");
        expectedMap.put("phones", phones);

        Map<String, Object> actualMap = jsonParser.parseJsonToMap(json);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    void parseSimpleJsonToMapSuccess() {
        String json = """
                {
                    "name": "Denis",
                    "age": 34,
                    "isActive": true
                }
                """;

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("name", "Denis");
        expectedMap.put("age", 34);
        expectedMap.put("isActive", true);

        Map<String, Object> actualMap = jsonParser.parseJsonToMap(json);
        assertEquals(expectedMap, actualMap);
    }

}