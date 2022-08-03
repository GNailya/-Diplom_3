package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MainPage {
    public  static String URL_MAIN = "https://stellarburgers.nomoreparties.site/";

    //Кнопка Войти в аккаунт
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement btnSignIn;

    //Кнопка Личный кабинет
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement btnPersonalArea;

    //Заголовок Соберите бургер
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement headAssembleBurger;


    // Раздел Булки
    @FindBy(how = How.XPATH, using = "//div[span[text()='Булки']]")
    private SelenideElement sectionBuns;
    // Ассортимент булок
    @FindBy(how = How.XPATH, using = ".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][1]")
    private SelenideElement listBuns;

    // Раздел Соусы
    @FindBy(how = How.XPATH, using = "//div[span[text()='Соусы']]")
    private SelenideElement sectionSauce;
    // Ассортимент соусов
    @FindBy(how = How.XPATH, using = ".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][2]")
    private SelenideElement listSauce;

    //Раздел Начинки
    @FindBy(how = How.XPATH, using = "//*[text()='Начинки']")
    private SelenideElement sectionFillings;
    // Ассортимент начинок
    @FindBy(how = How.XPATH, using = ".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][3]")
    private  SelenideElement listFilling;



    @Step("Клик на кнопку Войти в аккаунт")
    public LoginPage clickBtnSignIn() {
        btnSignIn.click();
        return Selenide.page(LoginPage.class);
    }
    @Step("Клик на кнопку Личный кабинет неавторизованным пользователем")
    public LoginPage clickBtnPersonalAreaNewUser() {
        btnPersonalArea.click();
        return Selenide.page(LoginPage.class);
    }
    @Step("Клик на кнопку Личный кабинет авторизованным пользователем")
    public ProfilePage clickBtnPersonalArea() {
        btnPersonalArea.shouldBe(visible).click();
        return Selenide.page(ProfilePage.class);
    }


    @Step("Клик на раздел Булки")
    public MainPage clickSectionBuns() {
        sectionBuns.shouldBe(visible).click();
        return this;
    }
    @Step("Отображение ассортимента булок")
    public boolean isListBuns() {
        return listBuns.isDisplayed();
    }

    @Step("Клик на раздел Соусы")
    public MainPage clickSectionSauce() {
        sectionSauce.click();
        return this;
    }
    @Step("Отображение ассортимента соусов")
    public boolean isListSauce() {
        return listSauce.isDisplayed();
    }

    @Step("Клик на раздел Начинки")
    public MainPage clickSectionFillings() {
        sectionFillings.click();
        return this;
    }
    @Step("Отображение ассортимента начинок")
    public boolean isListFillings() {
        return listFilling.isDisplayed();
    }

    @Step("Проверка загрузки страницы")
    public boolean isUrlMainPage() {
        headAssembleBurger.shouldBe(visible);
        return url().equals(URL_MAIN);
    }
}
