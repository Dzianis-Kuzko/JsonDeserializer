package ru.clevertec.json_deserializer;

import ru.clevertec.json_deserializer.api.IJsonDeserializer;
import ru.clevertec.json_deserializer.api.IJsonParser;
import ru.clevertec.json_deserializer.api.JsonField;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class JsonDeserializer implements IJsonDeserializer {
    private final IJsonParser jsonParser;

    public JsonDeserializer(IJsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public <T> T deserializeJson(String json, Class<T> clazz) throws Exception {
        Map<String, Object> jsonMap = jsonParser.parseJsonToMap(json);
        return convertMapToObject(jsonMap, clazz);
    }

    private <T> T convertMapToObject(Map<String, Object> jsonMap, Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = getFieldName(field);
            if (jsonMap.containsKey(fieldName)) {
                Object value = jsonMap.get(fieldName);
                field.setAccessible(true);
                Class<?> type = field.getType();
                if (type == String.class) {
                    field.set(instance, value.toString());
                } else if (type == int.class) {
                    field.set(instance, Integer.parseInt(value.toString()));
                } else if (type == boolean.class) {
                    field.set(instance, Boolean.parseBoolean(value.toString()));
                } else if (type.isArray()) {
                    setArrayField(instance, field, value);
                } else {
                    setObjectField(instance, field, value);
                }
            }
            field.setAccessible(false);
        }
        return instance;
    }

    private String getFieldName(Field field) {
        if (field.isAnnotationPresent(JsonField.class)) {
            return field.getAnnotation(JsonField.class).value();
        }
        return field.getName();
    }


    private void setArrayField(Object instance, Field field, Object value) throws Exception {
        Class<?> typeArray = field.getType().getComponentType();
        List<?> list = (List<?>) value;
        Object array = Array.newInstance(typeArray, list.size());
        for (int i = 0; i < list.size(); i++) {
            Array.set(array, i, list.get(i));
        }
        field.set(instance, array);
    }

    private void setObjectField(Object instance, Field field, Object value) throws Exception {
        Map<String, Object> nestedMap = (Map<String, Object>) value;
        Object nestedObject = convertMapToObject(nestedMap, field.getType());
        field.set(instance, nestedObject);
    }
}
