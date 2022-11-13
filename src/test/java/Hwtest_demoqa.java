import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Hwtest_demoqa {
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1220x780";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        String userName = "Кайрат";

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");


        $("#firstName").setValue(userName);
        $("#lastName").setValue("Кайратов");
        $("#userEmail").setValue("kairat_test@testtt.comm");
        $(".custom-control-label").click();
        $("#userNumber").setValue("87771111111");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1900");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__day--030").click();
        /*
        PS Другой вариант
        $(by("value", "6")).click();
        $(by("aria-label", "Choose Saturday, July 30th, 2022")).click();
        */
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("qwerty").pressTab();
        $(by("for", "hobbies-checkbox-1")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/homework.jpg"));
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(new ByText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(new ByText("Agra")).click();
        $("#submit").click();

       // Проверки
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(userName),
                text("kairat_test@testtt.comm"),
                text("Male"), text("8777111111"),
                text("30 July,1900"),
                text("Sports"), text("homework.JPG"),
                text("Some address 1"),
                text("Uttar Pradesh Agra")
        );
    }
}
