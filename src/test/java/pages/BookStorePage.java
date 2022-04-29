package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class BookStorePage {

    @Step("Открываем страницу Book Store")
    public BookStorePage openPage() {
        open("/books");
        return this;
    }

    @Step("Ищем книгу по названию издательства {0}")
    public BookStorePage setSearchWord(String testData) {
        $("#searchBox").setValue(testData);
        return this;
    }

    @Step("Проверяем, что найдены книги {0}")
    public BookStorePage checkBooksInResult(List<String> booksList) {
        for(String book : booksList) {
            $$(".rt-tbody").find(Condition.text(book)).should(Condition.visible);
        }
        return this;
    }
}
