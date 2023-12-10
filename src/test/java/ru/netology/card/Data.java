package ru.netology.card;

import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.devtools.idealized.Network;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Data {
    private Data() {
    }

    public static String generateData(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var cities = new String[]{
                "Москва", "Санкт-Петербург", "Калуга", "Владивосток", "Красноярск"
        };
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber().replace("(", "").replace(")", "").replace("+", "").replaceAll("-", "");
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
