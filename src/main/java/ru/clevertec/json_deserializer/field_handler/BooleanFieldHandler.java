package ru.clevertec.json_deserializer.field_handler;

import ru.clevertec.json_deserializer.util.ReflectionUtil;

import java.lang.reflect.Field;

public class BooleanFieldHandler implements FieldHandler {
    @Override
    public boolean supports(Field field) {
        return field.getType() == boolean.class;
    }

    @Override
    public void handleField(Object instance, Field field, Object value) throws Exception {
        ReflectionUtil.setFieldValue(instance, field, Boolean.parseBoolean(value.toString()));
    }
}
