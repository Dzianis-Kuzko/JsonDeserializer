package ru.clevertec.json_deserializer.value_handler;

import java.util.List;

public class ValueHandlerRegistry {
    private final List<ValueHandler> handlers;

    public ValueHandlerRegistry() {
        handlers = List.of(
                new IntValueHandler(),
                new BooleanValueHandler(),
                new StringValueHandler(),
                new ArrayValueHandler(this),
                new ObjectValueHandler(this)
        );
    }

    public Object handleValue(String value) {

        for (ValueHandler handler : handlers) {
            if (handler.canHandle(value)) {
                return handler.handle(value);
            }
        }
        throw new IllegalArgumentException("No handler found for value: " + value);
    }
}
