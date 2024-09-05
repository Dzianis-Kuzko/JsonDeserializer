package ru.clevertec.json_deserializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {

    public Map<String, Object> parseJsonToMap(String json) {
        json = json.trim();
        json = json.substring(1, json.length() - 1).trim();
        Map<String, Object> map = new HashMap<>();

        String[] pairs = splitJsonPairs(json);

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            String key = keyValue[0].trim().replaceAll("^\"|\"$", "");
            Object value = parseJsonValue(keyValue[1].trim());
            map.put(key, value);
        }

        return map;
    }

    private Object parseJsonValue(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        } else if (isNumber(value)) {
            return Integer.parseInt(value);
        } else if (value.startsWith("{") && value.endsWith("}")) {
            return parseJsonToMap(value);
        } else if (value.startsWith("[") && value.endsWith("]")) {
            return parseJsonArray(value);
        } else {
            return null;
        }
    }

    private List<Object> parseJsonArray(String jsonArray) {
        jsonArray = jsonArray.substring(1, jsonArray.length() - 1).trim();
        String[] elements = splitJsonPairs(jsonArray);
        List<Object> array = new ArrayList<>();

        for (String element : elements) {
            array.add(parseJsonValue(element.trim()));
        }

        return array;
    }

    private String[] splitJsonPairs(String json) {
        List<String> pairs = new ArrayList<>();
        StringBuilder currentPair = new StringBuilder();

        int depth = 0;

        for (char c : json.toCharArray()) {
            if (c == '{' || c == '[') {
                depth++;
            } else if (c == '}' || c == ']') {
                depth--;
            }

            if (c == ',' && depth == 0) {
                pairs.add(currentPair.toString());
                currentPair.setLength(0);
            } else {
                currentPair.append(c);
            }
        }

        pairs.add(currentPair.toString());
        return pairs.toArray(new String[0]);
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
