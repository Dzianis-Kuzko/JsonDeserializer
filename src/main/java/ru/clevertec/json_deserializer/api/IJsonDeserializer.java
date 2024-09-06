package ru.clevertec.json_deserializer.api;

public interface IJsonDeserializer {
    <T> T deserializeJson(String json, Class<T> clazz) throws Exception;
}
