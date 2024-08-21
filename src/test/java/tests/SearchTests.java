package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Мобильные тесты на Википедию")
public class SearchTests extends TestBase {

    @Test
    @DisplayName("Проверка работы поисковой строки")
    void successfulSearchTest() {
        step("Поиск в Википедии Appium", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверить, что нашли больше 0 статей", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Проверка открытия статьи из списка")
    void unsuccessfulOpenArticleTest() {
        step("Поиск в Википедии Appium", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Нажать на статью с заголовком Appium", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_title")).click() //.shouldHave(text("Appium")).click()
        );
        step("Проверить, что открылась страница с ошибкой", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(text("Error"))
        );
    }
}
