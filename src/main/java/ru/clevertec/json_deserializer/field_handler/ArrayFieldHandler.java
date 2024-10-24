package ru.clevertec.json_deserializer.field_handler;

import ru.clevertec.json_deserializer.util.ReflectionUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

public class ArrayFieldHandler implements FieldHandler {
    @Override
    public boolean supports(Field field) {
        return field.getType().isArray();
    }

    @Override
    public void handleField(Object instance, Field field, Object value) throws Exception {
        Class<?> typeArray = field.getType().getComponentType();
        List<?> list = (List<?>) value;
        Object array = Array.newInstance(typeArray, list.size());
        for (int i = 0; i < list.size(); i++) {
            Array.set(array, i, list.get(i));
        }
        ReflectionUtil.setFieldValue(instance, field, array);
    }
}
