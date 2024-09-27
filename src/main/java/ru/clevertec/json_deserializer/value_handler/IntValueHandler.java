package ru.clevertec.json_deserializer.value_handler;

public class IntValueHandler implements ValueHandler {
    @Override
    public boolean canHandle(String value) {
        return isNumber(value);
    }

    @Override
    public Object handle(String value) {
        return Integer.parseInt(value);
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
