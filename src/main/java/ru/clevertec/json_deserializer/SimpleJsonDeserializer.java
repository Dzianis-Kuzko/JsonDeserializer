package ru.clevertec.json_deserializer;

import ru.clevertec.json_deserializer.api.JsonDeserializer;
import ru.clevertec.json_deserializer.api.JsonParser;
import ru.clevertec.json_deserializer.field_handler.FieldHandler;
import ru.clevertec.json_deserializer.field_handler.FieldHandlerRegistry;
import ru.clevertec.json_deserializer.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class SimpleJsonDeserializer implements JsonDeserializer {
    private final JsonParser jsonParser;
    private final FieldHandlerRegistry fieldHandlerRegistry;

    public SimpleJsonDeserializer(JsonParser jsonParser, FieldHandlerRegistry fieldHandlerRegistry) {
        this.jsonParser = jsonParser;
        this.fieldHandlerRegistry = fieldHandlerRegistry;
    }

    @Override
    public <T> T deserializeJson(String json, Class<T> clazz) throws Exception {
        Map<String, Object> jsonMap = jsonParser.parseJsonToMap(json);
        return convertMapToObject(jsonMap, clazz);
    }

    public <T> T convertMapToObject(Map<String, Object> jsonMap, Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = ReflectionUtil.getFieldName(field);
            if (jsonMap.containsKey(fieldName)) {
                Object value = jsonMap.get(fieldName);
                FieldHandler handler = fieldHandlerRegistry.getHandlerForField(field);
                handler.handleField(instance, field, value);
            }
        }
        return instance;
    }

}
