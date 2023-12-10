package ru.netology.test;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.card.Data;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardAndDeliveryTest {
    @BeforeEach
    void setup(){
        open("http://localhost:9999");
    }



    @Test
    @DisplayName("ShouldSuccessfulPlan")
    void shouldSuccessfulPlan() {
        Data.UserInfo validUser = Data.Registration.generateUser("ru");
        int daysAddForFirst = 3;
        String firstDate = Data.generateData(daysAddForFirst);
        int daysAddForSecond = 4;
        String secondDate = Data.generateData(daysAddForSecond);
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(firstDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + firstDate)).shouldBe(Condition.visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(secondDate);
        $(".button__text").click();
        $(byText("Необходимо подтверждение")).shouldBe(Condition.visible);
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible);
        $(".button__text").click();
        $(byText("Успешно!")).shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + secondDate)).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

}
