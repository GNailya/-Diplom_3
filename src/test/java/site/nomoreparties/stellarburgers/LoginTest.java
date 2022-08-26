package site.nomoreparties.stellarburgers;

import api.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import model.UserCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static model.User.getRandomUser;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    private User user;
    private UserClient userClient;



    @Before
    public void setUp() {
        user = getRandomUser();
        userClient = new UserClient();
        userClient.registrationUser(user);

        //Configuration.browser = "firefox";
    }

    @After
    public void tearDown() {
        getWebDriver().quit();

        UserCredentials userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        Response response = userClient.login(userCredentials);
        if (response.body().jsonPath().getString("accessToken") != null) {
            userClient.delete(response);
        }

    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("После успешной авторизации пользователя редиректит на главную страницу")
    public void loginMainPageTest() {
        boolean isUrlMainPage = open(MainPage.URL_MAIN, MainPage.class)
                .clickBtnSignIn()
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .isUrlMainPage();

        assertTrue(isUrlMainPage);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("После успешной авторизации пользователя редиректит на главную страницу")
    public void loginBtnPersonalAreaTest() {
        boolean isUrlMainPage = open(MainPage.URL_MAIN, MainPage.class)
                .clickBtnPersonalAreaNewUser()
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .isUrlMainPage();

        assertTrue(isUrlMainPage);


    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("После успешной авторизации пользователя редиректит на главную страницу")
    public void loginLinkFormRegistrationTest() {
        boolean isUrlMainPage = open(RegistrationPage.URL_REGISTER, RegistrationPage.class)
                .clickRegistrationFormLinkToLoginForm()
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .isUrlMainPage();

        assertTrue(isUrlMainPage);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("После успешной авторизации пользователя редиректит на главную страницу")
    public void loginLinkFormResetPasswordTest() {
        boolean isUrlMainPage = open(ForgotPasswordPage.URL_FORGOT_PASSWORD, ForgotPasswordPage.class)
                .clickPasswordRecoveryFormLinkToLoginForm()
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .isUrlMainPage();

        assertTrue(isUrlMainPage);
    }
}
