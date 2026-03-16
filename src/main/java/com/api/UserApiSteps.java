package com.api;

import com.assets.Resources;
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
                .baseUri(Resources.BASE_API_URL);
    }

    @Step("Создание нового пользователя")
    public void userCreate(UserCreateRequest userCreateRequest) {
        requestSpecification()
                .body(userCreateRequest)
                .post(Resources.AUTH_API_URL)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse userLogin(UserLoginRequest userLoginRequest) {
        return requestSpecification()
                .body(userLoginRequest)
                .post(Resources.LOGIN_API_URL)
                .then();
    }

    @Step("Удаление пользователя без авторизации")
    public void userDelete(String accessToken) {
        requestSpecification()
                .header("Authorization", accessToken)
                .delete(Resources.USER_API_URL)
                .then();
    }

    @Step("Удаление пользователя после авторизации")
    public void userDeleteAfterLogin(UserLoginRequest userLoginRequest) {
        Response response = userLogin(userLoginRequest)
                .extract().response();
        UserLoginResponse userLoginResponse = response.as(UserLoginResponse.class);
        String accessToken = userLoginResponse.getAccessToken();
        if (accessToken != null) {
            userDelete(accessToken);
        }
    }

}
