package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    public final static String URL_REGISTER = "https://stellarburgers.nomoreparties.site/register";

    // Описание элементов на странице регистрации

    // Поле Имя
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement nameField;

    // Поле Email
    @FindBy(how = How.XPATH, using = "//*[text()='Email']/following-sibling::input")
    private SelenideElement emailField;

    // Поле Пароль
    @FindBy(how = How.XPATH, using = "//*[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordField;

    // Сообщение "Некорректный пароль"
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement invalidPasswordField;

    // Кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement btnRegister;

    // Ссылка Войти (если зарегистрирован)
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement linkToLoginForm;

    @Step("Введение имени")
    public RegistrationPage setName(String name) {
        nameField.setValue(name);
        return this;
    }
    @Step("Введение Email незарегистрированного пользователя пользователя")
    public RegistrationPage setEmail(String email) {
        emailField.setValue(email);
        return this;
    }
    @Step("Введение валидного пароля")
    public RegistrationPage setPassword(String password) {
        passwordField.setValue(password);
        return this;
    }
    @Step("Клик на кнопку Зарегистрироваться")
    public LoginPage clickBtnRegister() {
        btnRegister.click();
        return page(LoginPage.class);
    }
    @Step("Ошибка о невалидном пароле при клике на кнопку Зарегистрироваться")
    public boolean isMessagError() {
        btnRegister.click();
        return invalidPasswordField.isDisplayed();
    }
    @Step("Клик на ссылку войти на странице регистрации")
    public LoginPage clickRegistrationFormLinkToLoginForm() {
        linkToLoginForm.click();
        return page(LoginPage.class);
    }
}
