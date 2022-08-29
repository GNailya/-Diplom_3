package site.nomoreparties.stellarburgers;

import api.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import model.UserCredentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static model.User.getRandomUser;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private User user;
    private UserClient userClient;

    @Before
    public void setUp() {
        user = getRandomUser();
        userClient = new UserClient();

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
    @DisplayName("Создание пользователя с валидными данными")
    @Description("После успешной регистрации открывается страница авторизации")
    public void succesRegistrationTest() {

        boolean isUrlLoginPage = open(RegistrationPage.URL_REGISTER, RegistrationPage.class)
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickBtnRegister()
                .isUrlLoginPage();

        assertTrue(isUrlLoginPage);


    }

    @Test
    @DisplayName("Создание пользователя с некорректным паролем")
    @Description("Регистрация не происходит при введении некорректного пароля, возвращается ошибка")
    public void errorInvalidPasswordRegistrationTest() {
        String password = RandomStringUtils.randomAlphabetic(5);
        boolean isMessagError = open(RegistrationPage.URL_REGISTER, RegistrationPage.class)
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(password)
                .isMessagError();

        assertTrue("Некорректный пароль", isMessagError);
    }
}
