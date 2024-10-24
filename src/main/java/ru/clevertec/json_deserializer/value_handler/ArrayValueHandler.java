package ru.clevertec.json_deserializer.value_handler;

import ru.clevertec.json_deserializer.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ArrayValueHandler implements ValueHandler {
    private final ValueHandlerRegistry handlerRegistry;

    public ArrayValueHandler(ValueHandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public boolean canHandle(String value) {
        return value.startsWith("[") && value.endsWith("]");
    }

    @Override
    public Object handle(String value) {
        value = value.substring(1, value.length() - 1).trim();
        String[] elements = JsonUtil.splitJsonPairs(value);

        List<Object> array = new ArrayList<>();
        for (String element : elements) {
            array.add(handlerRegistry.handleValue(element.trim()));
        }
        return array;
    }
}
