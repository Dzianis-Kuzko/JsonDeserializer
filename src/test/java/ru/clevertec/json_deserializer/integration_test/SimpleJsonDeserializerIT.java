package ru.clevertec.json_deserializer.integration_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.json_deserializer.SimpleJsonDeserializer;
import ru.clevertec.json_deserializer.SimpleJsonParser;
import ru.clevertec.json_deserializer.api.JsonDeserializer;
import ru.clevertec.json_deserializer.api.JsonParser;
import ru.clevertec.json_deserializer.field_handler.FieldHandlerRegistry;
import ru.clevertec.json_deserializer.test_data.TestJsonFactory;
import ru.clevertec.json_deserializer.test_data.TestUserFactory;
import ru.clevertec.json_deserializer.test_data.UserTest;
import ru.clevertec.json_deserializer.value_handler.ValueHandlerRegistry;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleJsonDeserializerIT {
    private JsonDeserializer jsonDeserializer;

    @BeforeEach
    void setUp() throws Exception {
        JsonParser jsonParser = new SimpleJsonParser(new ValueHandlerRegistry());
        jsonDeserializer = new SimpleJsonDeserializer(jsonParser, new FieldHandlerRegistry());
    }

    @Test
    void deserializeJsonWithAnnotationSuccessIT() throws Exception {
        String json = TestJsonFactory.createJsonWithArrayAndObject();

        UserTest expectedUser = TestUserFactory.createUserTest();

        UserTest actualUser = jsonDeserializer.deserializeJson(json, UserTest.class);

        assertEquals(expectedUser, actualUser);

    }
}