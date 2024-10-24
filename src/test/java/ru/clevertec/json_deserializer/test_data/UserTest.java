package ru.clevertec.json_deserializer.test_data;

import ru.clevertec.json_deserializer.api.JsonField;

import java.util.Arrays;
import java.util.Objects;

public class UserTest {
    private String name;
    private int age;
    @JsonField(value ="is_active")
    private boolean isActive;
    private AddressTest address;
    private String [] phones;

    public UserTest() {
    }

    public UserTest(String name, int age, boolean isActive, AddressTest address, String[] phones) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.address = address;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public AddressTest getAddress() {
        return address;
    }

    public void setAddress(AddressTest address) {
        this.address = address;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTest user = (UserTest) o;
        return age == user.age && isActive == user.isActive && Objects.equals(name, user.name) && Objects.equals(address, user.address) && Arrays.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + age;
        result = 31 * result + Boolean.hashCode(isActive);
        result = 31 * result + Objects.hashCode(address);
        result = 31 * result + Arrays.hashCode(phones);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                ", address=" + address +
                ", phones=" + Arrays.toString(phones) +
                '}';
    }
}
