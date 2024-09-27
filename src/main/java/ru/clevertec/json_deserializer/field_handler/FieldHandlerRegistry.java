package ru.clevertec.json_deserializer.field_handler;

import ru.clevertec.json_deserializer.SimpleJsonDeserializer;
import ru.clevertec.json_deserializer.SimpleJsonParser;
import ru.clevertec.json_deserializer.value_handler.ValueHandlerRegistry;

import java.lang.reflect.Field;
import java.util.List;

public class FieldHandlerRegistry {
    private final List<FieldHandler> handlers;

    public FieldHandlerRegistry() {
        handlers = List.of(
                new StringFieldHandler(),
                new IntFieldHandler(),
                new BooleanFieldHandler(),
                new ArrayFieldHandler(),
                new ObjectFieldHandler(new SimpleJsonDeserializer(new SimpleJsonParser(new ValueHandlerRegistry()), this))
        );
    }

    public FieldHandler getHandlerForField(Field field) {
        return handlers.stream()
                .filter(handler -> handler.supports(field))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No handler found for field: " + field.getName()));
    }
}
