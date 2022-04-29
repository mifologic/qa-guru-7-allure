package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.BookStorePage;

import java.util.List;
import java.util.stream.Stream;

public class AnnotatedStepsTest {

    BookStorePage bookStorePage = new BookStorePage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    static Stream<Arguments> booksByPublisherData() {
        return Stream.of(
                Arguments.of(
                        "No Starch Press",
                        List.of(
                                "Eloquent JavaScript, Second Edition",
                                "Understanding ECMAScript 6")),
                Arguments.of(
                        "O'Reilly Media",
                        List.of(
                                "Git Pocket Guide",
                                "Learning JavaScript Design Patterns",
                                "Designing Evolvable Web APIs with ASP.NET",
                                "Speaking JavaScript",
                                "You Don't Know JS",
                                "Programming JavaScript Applications")));
    }

    @MethodSource("booksByPublisherData")
    @ParameterizedTest(name="Ищем книгу по названию издательства")
    void checkBooksByPublisher(String publisher, List<String> books) {
        SelenideLogger.addListener("allure", new AllureSelenide());

        bookStorePage.openPage()
                .setSearchWord(publisher)
                .checkBooksInResult(books);
    }

}
