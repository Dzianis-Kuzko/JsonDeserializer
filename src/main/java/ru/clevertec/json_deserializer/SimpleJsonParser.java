package ru.clevertec.json_deserializer;

import ru.clevertec.json_deserializer.api.JsonParser;
import ru.clevertec.json_deserializer.util.JsonUtil;
import ru.clevertec.json_deserializer.value_handler.ValueHandlerRegistry;

import java.util.HashMap;
import java.util.Map;

public class SimpleJsonParser implements JsonParser {
    private final ValueHandlerRegistry valueHandlerRegistry;

    public SimpleJsonParser(ValueHandlerRegistry valueHandlerRegistry) {
        this.valueHandlerRegistry = valueHandlerRegistry;
    }

    @Override
    public Map<String, Object> parseJsonToMap(String json) {
        json = json.trim();
        json = json.substring(1, json.length() - 1).trim();
        Map<String, Object> map = new HashMap<>();

        String[] pairs = JsonUtil.splitJsonPairs(json);

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if(keyValue.length< 2){
                throw new IllegalArgumentException("Invalid key-value pair: " + pair);
            }
            String key = keyValue[0].trim().replaceAll("^\"|\"$", "");
            Object value = parseJsonValue(keyValue[1].trim());
            map.put(key, value);
        }

        return map;
    }

    private Object parseJsonValue(String value) {
        return valueHandlerRegistry.handleValue(value);
    }

}
