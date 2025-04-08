package tests.api.api;

import tests.api.model.account.LoginRequestModel;

import static io.restassured.RestAssured.given;
import static tests.api.specs.ApiSpecs.requestSpec;
import static tests.api.specs.ApiSpecs.responseSpec;

public class UserApi {
    public String getSessionId(LoginRequestModel loginRequest) {
        return given(requestSpec)
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .body(loginRequest.convertToBody())
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .extract().cookie("sessionId");
    }
}
