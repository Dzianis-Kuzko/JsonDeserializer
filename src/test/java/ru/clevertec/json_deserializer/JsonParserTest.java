package ru.clevertec.json_deserializer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.json_deserializer.api.IJsonParser;
import ru.clevertec.json_deserializer.test_data.TestJsonFactory;
import ru.clevertec.json_deserializer.test_data.TestMapFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {
    private IJsonParser jsonParser;

    @BeforeEach
    void setUp() {
        jsonParser = new JsonParser();
    }

    @Test
    void parseJsonWithArrayAndObjectToMapSuccess() {
        String json = TestJsonFactory.createJsonWithArrayAndObject();
        Map<String, Object> expectedMap = TestMapFactory.createMapForJsonWithArrayAndObject();
        Map<String, Object> actualMap = jsonParser.parseJsonToMap(json);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    void parseSimpleJsonToMapSuccess() {
        String json = """
                {
                    "name": "Denis",
                    "age": 34,
                    "is_active": true
                }
                """;

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("name", "Denis");
        expectedMap.put("age", 34);
        expectedMap.put("is_active", true);

        Map<String, Object> actualMap = jsonParser.parseJsonToMap(json);
        assertEquals(expectedMap, actualMap);
    }
}