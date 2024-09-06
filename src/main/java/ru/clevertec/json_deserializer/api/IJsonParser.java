package ru.clevertec.json_deserializer.api;

import java.util.Map;

public interface IJsonParser {
    Map<String, Object> parseJsonToMap(String json);
}
