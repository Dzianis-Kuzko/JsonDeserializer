package ru.clevertec.json_deserializer.field_handler;

import java.lang.reflect.Field;

public interface FieldHandler {
    boolean supports(Field field);
    void handleField(Object instance, Field field, Object value) throws Exception;
}
