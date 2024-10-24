package ru.clevertec.json_deserializer.util;

import ru.clevertec.json_deserializer.api.JsonField;

import java.lang.reflect.Field;

public class ReflectionUtil {
    public static void setFieldValue(Object instance, Field field, Object value) throws Exception {
        field.setAccessible(true);
        field.set(instance, value);
        field.setAccessible(false);
    }

    public static String getFieldName(Field field) {
        if (field.isAnnotationPresent(JsonField.class)) {
            return field.getAnnotation(JsonField.class).value();
        }
        return field.getName();
    }

}
