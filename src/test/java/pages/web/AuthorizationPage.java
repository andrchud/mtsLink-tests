package pages.web;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {

    private final SelenideElement
            emailInput = $(byName("email")),
            passwordInput = $(byName("password")),
            submitButton = $(byAttribute("data-testid", "SignIn.action.submit")),
            profileImage = $(byAttribute("data-testid", "Meetings.c.User"));


    @Step("Открыть страницу /signin")
    public AuthorizationPage openPage() {
        open("/signin");
        return this;
    }

    @Step("Указать email")
    public AuthorizationPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Указать пароль")
    public AuthorizationPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку Войти")
    public AuthorizationPage clickSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Проверить что пользователь попал в личный кабинет")
    public void checkSuccessfulAuthorization() {
        profileImage.shouldHave(visible);
    }
}
