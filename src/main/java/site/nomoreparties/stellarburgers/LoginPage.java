package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPage {
    public final static String URL_LOGIN = "https://stellarburgers.nomoreparties.site/login";

    //Текст Вход на странице авторизации
    @FindBy(xpath = "//h2[text()='Вход']")
    private SelenideElement textGoLoginPage;

    //Поле Email
    @FindBy(xpath = "//label[text()='Email']//following-sibling::input")
    private SelenideElement emailField;

    //Поле Пароль
    @FindBy(xpath = "//label[text()='Пароль']//following-sibling::input")
    private SelenideElement passwordField;

    //Кнопка Войти
    @FindBy(xpath = "//button[text()='Войти']")
    private SelenideElement btnLogin;


    @Step("Введение email зарегистрированного пользователя")
    public LoginPage setEmailUser(String email) {
        emailField.setValue(email);
        return this;
    }
    @Step("Введение пароля зарегистрированного пользователя")
    public LoginPage setPasswordUser(String password) {
        passwordField.setValue(password);
        return this;
    }
    @Step("Клик на кнопку Войти")
    public MainPage clickBtnLogin() {
        btnLogin.click();
        return page(MainPage.class);
    }
    @Step("Проверка загрузки страницы")
    public boolean isUrlLoginPage() {
        textGoLoginPage.shouldBe(Condition.visible);
        return url().equals(URL_LOGIN);
    }
}
