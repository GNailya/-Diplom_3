package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;
import static site.nomoreparties.stellarburgers.MainPage.URL_MAIN;

public class ConstructorTest {
    @After
    public void tearDown(){getWebDriver().quit();}

    @Test
    @DisplayName("Переход к разделу соусы")
    @Description("При клике на кнопку Соусы, появляется ассортимент соусов")
    public void goSauceSectionTest() {
        boolean isListSauce = open(URL_MAIN, MainPage.class)
                .clickSectionSauce()
                .isListSauce();

        assertTrue(isListSauce);
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("При клике на кнопку Булки, появляется ассортимент булочек")
    public void goBunsSectionTest() {
        boolean isListBuns = open(URL_MAIN, MainPage.class)
                .clickSectionSauce()
                .clickSectionBuns()
                .isListBuns();

        assertTrue(isListBuns);
    }
    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("При клике на кнопку Начинки, появляется ассортимент начинок")
    public void goFillingsSectionTest() {
        boolean isListFillings = open(URL_MAIN, MainPage.class)
                .clickSectionFillings()
                .isListFillings();

        assertTrue(isListFillings);
    }
}
