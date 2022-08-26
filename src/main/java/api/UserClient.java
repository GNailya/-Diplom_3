package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;
import model.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {
    private final String LOGIN = "login/";
    private final String REGISTER = "register/";
    private final String DELETE = "auth/user/";

    public Response registrationUser(User user) {
        return (Response) given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REGISTER);

    }

    public Response login(UserCredentials creds) {
        return (Response) given()
                .spec(getSpec())
                .body(creds)
                .when()
                .post(LOGIN);

    }

    @Step("Удаление пользователя")
    public Response delete(Response response) {
        String accessToken = response.body().jsonPath().getString("accessToken");
        return given()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE);

    }
}