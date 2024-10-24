package ru.clevertec.json_deserializer.field_handler;

import ru.clevertec.json_deserializer.SimpleJsonDeserializer;
import ru.clevertec.json_deserializer.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class ObjectFieldHandler implements FieldHandler {
    private final SimpleJsonDeserializer jsonDeserializer;

    public ObjectFieldHandler(SimpleJsonDeserializer jsonDeserializer) {
        this.jsonDeserializer = jsonDeserializer;
    }

    @Override
    public boolean supports(Field field) {
        return !field.getType().isPrimitive() && !field.getType().isArray();
    }

    @Override
    public void handleField(Object instance, Field field, Object value) throws Exception {
        if (value instanceof Map) {
            Map<String, Object> nestedMap = (Map<String, Object>) value;
            Object nestedObject = jsonDeserializer.convertMapToObject(nestedMap, field.getType());
            ReflectionUtil.setFieldValue(instance, field, nestedObject);
        } else {
            throw new IllegalArgumentException("Expected a Map but found: " + value.getClass().getSimpleName());
        }
    }
}
