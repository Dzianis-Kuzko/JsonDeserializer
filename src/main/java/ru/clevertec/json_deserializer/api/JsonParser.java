package ru.clevertec.json_deserializer.api;

import java.util.Map;

public interface JsonParser {
    Map<String, Object> parseJsonToMap(String json);
}
