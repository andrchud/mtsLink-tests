package tests;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    Faker faker = new Faker(new Locale("ru"));

    public final String email = "andr.chud2010@yandex.ru", password = "4330948An";

    public final String
            name = faker.name().firstName(),
            secondName = faker.name().lastName(),
            nickname = faker.name().username(),
            phone = faker.phoneNumber().subscriberNumber(10),
            organization = faker.company().name(),
            position = faker.company().profession(),
            description = "Character = " + faker.backToTheFuture().character() +
                    "Date = " + faker.backToTheFuture().date(),
            eventName = faker.elderScrolls().race();

}
