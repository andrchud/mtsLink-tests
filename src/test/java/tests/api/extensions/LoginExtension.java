package tests.api.extensions;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import tests.TestData;
import tests.api.api.UserApi;
import tests.api.model.account.LoginRequestModel;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        UserApi userApi = new UserApi();
        TestData testData = new TestData();
        LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);
        String sessionId = userApi.getSessionId(loginRequest);

        open("/logo.73c0c1c921744210f1b3babdc1e1aa60.svg");
        getWebDriver().manage().addCookie(new Cookie("sessionId", sessionId));
    }
}
