package ru.clevertec.json_deserializer.util;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static String[] splitJsonPairs(String json) {
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
}
