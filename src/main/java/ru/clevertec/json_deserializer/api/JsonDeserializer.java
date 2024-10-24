package ru.clevertec.json_deserializer.api;

public interface JsonDeserializer {
    <T> T deserializeJson(String json, Class<T> clazz) throws Exception;
}
