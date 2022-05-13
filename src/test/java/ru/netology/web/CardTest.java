package ru.netology.web;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardTest {

    public static String meetingDate() {
        LocalDate date = LocalDate.now();
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }
    @Test
    void successfulCardOrder() {
        $("[placeholder='Город']").setValue("Чита");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(meetingDate());
        $(By.name("name")).setValue("Татьяна Соловьева");
        $(By.name("phone")).setValue("+79003003496");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).should(visible, Duration.ofSeconds(15));
    }
    @Test
    void successfulCardOrder2() {
        $("[placeholder='Город']").setValue("Чита");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(meetingDate());
        $(By.name("name")).setValue("Татьяна Иванова-Петрова");
        $(By.name("phone")).setValue("+79003003496");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).should(visible, Duration.ofSeconds(15));
    }
}
