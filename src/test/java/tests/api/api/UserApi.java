package tests.api.api;

import tests.api.model.account.LoginRequestModel;

import static io.restassured.RestAssured.given;
import static tests.api.specs.ApiSpecs.*;

public class UserApi {
    public String getSessionId(LoginRequestModel loginRequest) {
        return given(baseRequestSpec)
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .body(loginRequest.convertToBody())
                .when()
                .post("/login")
                .then()
                .spec(successResponseSpec)
                .extract().cookie("sessionId");
    }
}
