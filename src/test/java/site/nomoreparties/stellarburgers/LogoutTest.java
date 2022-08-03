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
import static org.junit.Assert.assertTrue;

public class LogoutTest {
    User user;

    @Before
    public void setUp() {
        user = User.getRandomUser();
        UserClient.registrationUser(user);
    }
    @After
    public void tearDown() {

        UserCredentials userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        Response response = UserClient.login(userCredentials);
        if (response.body().jsonPath().getString("accessToken") != null) {
            UserClient.delete(response);
        }
    }
    @Test
    @DisplayName("Выход из личного кабинета")
    @Description("После клика на кнопку Выход пользователь переходит на страницу авторизации ")
    public void logoutFromProfilePageTest() {
        boolean isUrlLoginPage = open(LoginPage.URL_LOGIN, LoginPage.class)
                .setEmailUser(user.getEmail())
                .setPasswordUser(user.getPassword())
                .clickBtnLogin()
                .clickBtnPersonalArea()
                .clickLogout()
                .isUrlLoginPage();

        assertTrue(isUrlLoginPage);
    }
}
