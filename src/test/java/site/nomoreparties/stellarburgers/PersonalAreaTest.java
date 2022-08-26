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
import static site.nomoreparties.stellarburgers.LoginPage.URL_LOGIN;
import static site.nomoreparties.stellarburgers.MainPage.URL_MAIN;

public class PersonalAreaTest {
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
    @DisplayName("Переход в личный кабинет авторизованным пользователем -> открывается личный кабинет")
    @Description("При клике на Личный кабинет,авторизованного пользователя редиректит на страницу Профиль")
    public void goProfileTest() {
        boolean isUrlProfilePage = open(URL_LOGIN, LoginPage.class)
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .clickBtnPersonalArea()
                .isUrlProfilePage();

        assertTrue(isUrlProfilePage);

    }

    @Test
    @DisplayName("Переход в личный кабинет неавторизованным пользователем")
    @Description("При клике на Личный кабинет, неавторизованного пользователя редиректит на страницу авторизации")
    public void goProfileNewUserTest() {
        boolean isUrlLoginPage = open(URL_MAIN, MainPage.class)
                .clickBtnPersonalAreaNewUser()
                .isUrlLoginPage();

        assertTrue(isUrlLoginPage);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор  -> открывается главная страница")
    public void goFromProfilePageToConstructorTest() {
        boolean isUrlMainPage = open(URL_LOGIN, LoginPage.class)
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .clickBtnPersonalArea()
                .clickConstructor()
                .isUrlMainPage();

        assertTrue(isUrlMainPage);
    }

    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers -> открывается главная страница")
    public void goFromProfilePageToLogoTest() {
        boolean isUrlMainPage = open(URL_LOGIN, LoginPage.class)
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .clickBtnPersonalArea()
                .clicklogoStellarBurgers()
                .isUrlMainPage();

        assertTrue(isUrlMainPage);
    }
}
