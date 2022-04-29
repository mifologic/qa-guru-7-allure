package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

public class SelenideListenerTest {

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

        open("/books");
        $("#searchBox").setValue(testData);
        $$(".rt-tbody").find(Condition.text(bookTitle)).should(Condition.visible);
    }
}