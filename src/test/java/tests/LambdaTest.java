package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LambdaTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @CsvSource(value = {
            "JavaScript, Programming JavaScript Applications "
    })
    @ParameterizedTest(name = "Find book about {0} with expected title {1}")
    void findBookInBookStoreTest(String testData, String bookTitle) {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем страницу BookStore\"", () -> {
            open("/books");
        });
        step("Вводим слово для поиска книги: " + testData, () -> {
            $("#searchBox").setValue(testData);
        });

        step("Проверяем, что найдена книга: " + bookTitle, () -> {
            $$(".rt-tbody").find(Condition.text(bookTitle)).should(Condition.visible);
        });
    }

}
