package ru.clevertec.json_deserializer.integration_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.json_deserializer.JsonDeserializer;
import ru.clevertec.json_deserializer.JsonParser;
import ru.clevertec.json_deserializer.api.IJsonDeserializer;
import ru.clevertec.json_deserializer.test_data.TestJsonFactory;
import ru.clevertec.json_deserializer.test_data.TestUserFactory;
import ru.clevertec.json_deserializer.test_data.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDeserializerIT {
    private IJsonDeserializer jsonDeserializer;

    @BeforeEach
    void setUp() {
        jsonDeserializer = new JsonDeserializer(new JsonParser());
    }

    @Test
    void deserializeJsonWithAnnotationSuccessIT() throws Exception {
        String json = TestJsonFactory.createJsonWithArrayAndObject();

        UserTest expectedUser = TestUserFactory.createUserTest();

        UserTest actualUser = jsonDeserializer.deserializeJson(json, UserTest.class);

        assertEquals(expectedUser, actualUser);

    }
}