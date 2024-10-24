package ru.clevertec.json_deserializer.value_handler;

public class BooleanValueHandler implements ValueHandler {
    @Override
    public boolean canHandle(String value) {
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    @Override
    public Object handle(String value) {
        return Boolean.parseBoolean(value);
    }
}
