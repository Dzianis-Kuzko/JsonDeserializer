package ru.clevertec.json_deserializer.value_handler;

import ru.clevertec.json_deserializer.SimpleJsonParser;

public class ObjectValueHandler implements ValueHandler {
    private final ValueHandlerRegistry handlerRegistry;

    public ObjectValueHandler(ValueHandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public boolean canHandle(String value) {
        return value.startsWith("{") && value.endsWith("}");
    }

    @Override
    public Object handle(String value) {
        SimpleJsonParser parser = new SimpleJsonParser(handlerRegistry);

        return parser.parseJsonToMap(value);
    }
}
