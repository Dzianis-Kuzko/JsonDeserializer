package ru.clevertec.json_deserializer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.json_deserializer.field_handler.FieldHandlerRegistry;
import ru.clevertec.json_deserializer.test_data.TestJsonFactory;
import ru.clevertec.json_deserializer.test_data.TestMapFactory;
import ru.clevertec.json_deserializer.test_data.TestUserFactory;
import ru.clevertec.json_deserializer.test_data.UserTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleJsonDeserializerTest {
    @Mock
    private SimpleJsonParser simpleJsonParser;
    @Spy
    private FieldHandlerRegistry fieldHandlerRegistry;
    @InjectMocks
    private SimpleJsonDeserializer simpleJsonDeserializer;

    @Test
    void deserializeJsonWithAnnotationSuccess() throws Exception {
        String json = TestJsonFactory.createJsonWithArrayAndObject();
        Map<String, Object> map = TestMapFactory.createMapForJsonWithArrayAndObject();

        when(simpleJsonParser.parseJsonToMap(json)).thenReturn(map);

        UserTest expectedUser = TestUserFactory.createUserTest();
        UserTest actualUser = simpleJsonDeserializer.deserializeJson(json, UserTest.class);

        assertEquals(expectedUser, actualUser);

    }
}