package ru.clevertec.json_deserializer.test_data;

public class TestUserFactory {
    public static UserTest createUserTest(){
        UserTest user = new UserTest();
        user.setName("Denis");
        user.setAge(34);
        user.setActive(true);
        AddressTest addressTest = new AddressTest();
        addressTest.setCity("Minsk");
        addressTest.setStreet("Mira");
        addressTest.setHouseNumber("158");
        user.setAddress(addressTest);
        user.setPhones(new String[]{"+375336951582", "+375331234567"});
        return user;
    }
}
