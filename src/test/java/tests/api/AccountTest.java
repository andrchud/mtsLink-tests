package tests.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestData;
import tests.api.api.UserApi;
import tests.api.model.account.LoginRequestModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static tests.api.specs.ApiSpecs.*;

@Epic("Api")
@Feature("Личный кабинет")
@Tag("api")
public class AccountTest extends TestBase {
    UserApi userApi = new UserApi();
    TestData testData = new TestData();
    LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);
    String sessionId = userApi.getSessionId(loginRequest);

    @Test
    @DisplayName("Авторизация по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);

        step("Авторизоваться по почте и паролю", () ->
                given(baseRequestSpec)
                        .contentType("application/x-www-form-urlencoded; charset=utf-8")
                        .body(loginRequest.convertToBody())
                        .when()
                        .post("/login")
                        .then()
                        .spec(successResponseSpec)
        );
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Severity(SeverityLevel.BLOCKER)
    void logoutTest() {
        step("Выйти из аккаунта", () ->
                given(baseRequestSpec)
                        .cookie("sessionId", sessionId)
                        .contentType("application/x-www-form-urlencoded; charset=utf-8")
                        .when()
                        .post("/logout")
                        .then()
                        .spec(logoutResponseSpec)
        );
    }

}
