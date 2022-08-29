package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    public final static String URL_FORGOT_PASSWORD = "https://stellarburgers.nomoreparties.site/forgot-password";

    // Ссылка перехода на страницу авторизации
    @FindBy(xpath = "//a[text()='Войти']")
    private SelenideElement linkPasswordRecoveryToLoginForm;

    @Step("Клик на ссылку войти на странице восстановления пароля")
    public LoginPage clickPasswordRecoveryFormLinkToLoginForm() {
        linkPasswordRecoveryToLoginForm.click();
        return page(LoginPage.class);
    }
}
