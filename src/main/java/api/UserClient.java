package api;
import io.restassured.response.Response;
import model.User;
import model.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient{
    public static Response registrationUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(ROOT + "register/");

    }

    public static Response login(UserCredentials creds) {
        return given()
                .spec(getSpec())
                .body(creds)
                .when()
                .post(ROOT + "login/");

    }

    public static Response delete(Response response) {
        String accessToken = response.body().jsonPath().getString("accessToken");
        return given()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(ROOT + "user");
    }
}
