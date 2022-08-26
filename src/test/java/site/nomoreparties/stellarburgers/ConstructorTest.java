package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static org.junit.Assert.assertTrue;
import static site.nomoreparties.stellarburgers.MainPage.URL_MAIN;

public class ConstructorTest {

    @Before
    public void setUp() {
        //Configuration.browser = "firefox";
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Переход к разделу соусы")
    @Description("При клике на кнопку Соусы, отображается раздел соусов")
    public void goSauceSectionTest() {
        boolean isDisplaySectionSauce = open(URL_MAIN, MainPage.class)
                .clickSectionSauce()
                .isDisplaySectionSauce();

        assertTrue(isDisplaySectionSauce);
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("При клике на кнопку Булки, отображается раздел булочек")
    public void goBunsSectionTest() {
        boolean isDisplaySectionBuns = open(URL_MAIN, MainPage.class)
                .clickSectionSauce()
                .clickSectionBuns()
                .isDisplaySectionBuns();

        assertTrue(isDisplaySectionBuns);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("При клике на кнопку Начинки, отображается раздел начинок")
    public void goFillingsSectionTest() {
        boolean isDisplaySectionFilling = open(URL_MAIN, MainPage.class)
                .clickSectionFilling()
                .isDisplaySectionFilling();

        assertTrue(isDisplaySectionFilling);
    }
}
