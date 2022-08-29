package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MainPage {
    public static String URL_MAIN = "https://stellarburgers.nomoreparties.site/";

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
    private SelenideElement btnBuns;

    // Раздел Соусы
    @FindBy(how = How.XPATH, using = "//div[span[text()='Соусы']]")
    private SelenideElement btnSauce;

    //Раздел Начинки
    @FindBy(how = How.XPATH, using = "//*[text()='Начинки']")
    private SelenideElement btnFillings;

    //наименования класса  после выбора раздела
    @FindBy(how = How.CLASS_NAME, using = "tab_tab_type_current__2BEPc")
    private SelenideElement sectionIngredients;


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
        btnBuns.click();
        return this;
    }

    @Step("Переход в раздел Булки")
    public boolean isDisplaySectionBuns() {
        return sectionIngredients.getText().contentEquals("Булки");
    }
    @Step("Клик на раздел Соусы")
    public MainPage clickSectionSauce() {
        btnSauce.click();
        return this;
    }

    @Step("Переход в раздел Соусы")
    public boolean isDisplaySectionSauce() {
        return sectionIngredients.getText().contentEquals("Соусы");
    }

    @Step("Клик на раздел Начинки")
    public MainPage clickSectionFilling() {
        btnFillings.click();
        return this;
    }

    @Step("переход в раздел Начинки")
    public boolean isDisplaySectionFilling() {
        return sectionIngredients.getText().contentEquals("Начинки");
    }

    @Step("Проверка загрузки страницы")
    public boolean isUrlMainPage() {
        headAssembleBurger.shouldBe(visible);
        return url().equals(URL_MAIN);
    }

}
