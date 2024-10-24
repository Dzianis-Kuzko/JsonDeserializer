package ru.clevertec.json_deserializer.value_handler;

public interface ValueHandler {
    boolean canHandle(String value);
    Object handle(String value);
}
