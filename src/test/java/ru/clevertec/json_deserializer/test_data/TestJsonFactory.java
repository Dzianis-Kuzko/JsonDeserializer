package ru.clevertec.json_deserializer.test_data;

public class TestJsonFactory {
    public static String createJsonWithArrayAndObject(){
        return """
                {
                    "name": "Denis",
                    "age": 34,
                    "is_active": true,
                    "address": {
                        "city": "Minsk",
                        "street": "Mira",
                        "house_number": "158"
                    },
                    "phones": ["+375336951582", "+375331234567"]
                }
                """;
    }
}
