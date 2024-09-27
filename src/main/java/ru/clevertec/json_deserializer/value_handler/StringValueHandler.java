package ru.clevertec.json_deserializer.value_handler;

public class StringValueHandler implements ValueHandler {
    @Override
    public boolean canHandle(String value) {
        return value.startsWith("\"") && value.endsWith("\"");
    }

    @Override
    public Object handle(String value) {
        return value.substring(1, value.length() - 1);
    }
}
