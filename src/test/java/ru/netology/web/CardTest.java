package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    @Test
    void successfulCardOrder() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Чита");
        $("[placeholder='Дата встречи']").doubleClick().append("20.05.2022");
        $(By.name("name")).setValue("Татьяна Соловьева");
        $(By.name("phone")).setValue("+79003003496");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).should(visible, Duration.ofSeconds(15));
    }
}
