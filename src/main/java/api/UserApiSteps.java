package api;

import Assets.Resources;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApiSteps {

    public static RequestSpecification requestSpecification() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Resources.baseApiUrl);
    }

    @Step("Создание нового пользователя")
    public void userCreate(UserCreateRequest userCreateRequest) {
        requestSpecification()
                .body(userCreateRequest)
                .post(Resources.authApiUrl)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse userLogin(UserLoginRequest userLoginRequest) {
        return requestSpecification()
                .body(userLoginRequest)
                .post(Resources.loginApiUrl)
                .then();
    }

    @Step("Удаление пользователя без авторизации")
    public void userDelete(String accessToken) {
        requestSpecification()
                .header("Authorization", accessToken)
                .delete(Resources.userApiUrl)
                .then();
    }

    @Step("Удаление пользователя после авторизации")
    public void userDeleteAfterLogin(UserLoginRequest userLoginRequest) {
        Response response = userLogin(userLoginRequest)
                .extract().response();
        UserLoginResponse userLoginResponse = response.as(UserLoginResponse.class);
        String accessToken = userLoginResponse.getAccessToken();
        userDelete(accessToken);
    }

}
