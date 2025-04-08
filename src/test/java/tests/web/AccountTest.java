package tests.web;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.web.AuthorizationPage;
import pages.web.ProfilePage;
import pages.web.ToolBarPage;
import tests.TestData;
import tests.api.extensions.WithLogin;

import static com.codeborne.selenide.Selenide.open;

public class AccountTest extends TestBase {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    TestData testData = new TestData();
    ToolBarPage toolBarPage = new ToolBarPage();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Авторизация по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        authorizationPage.openPage("/signin")
                .setEmail(testData.email)
                .setPassword(testData.password)
                .clickSubmit()
                .checkSuccessfulAuthorization();
    }

    @Test
    @WithLogin
    @DisplayName("Изменение информации в профиле")
    @Severity(SeverityLevel.NORMAL)
    void changeProfileDataTest() {
        toolBarPage.openPage("/meetings")
                .clickAvatar()
                .clickProfile();

        profilePage.setName(testData.name)
                .setSecondName(testData.secondName)
                .setNickname(testData.nickname)
                .setPhone(testData.phone)
                .setOrganization(testData.organization)
                .setPosition(testData.position)
                .setDescription(testData.description)
                .clickSave();

        toolBarPage.clickAvatar()
                .clickProfile();

        profilePage.checkName(testData.name)
                .checkSecondName(testData.secondName)
                .checkNickname(testData.nickname)
                .checkPhone(testData.phone)
                .checkOrganization(testData.organization)
                .checkPosition(testData.position)
                .checkDescription(testData.description);
    }

    @Test
    @WithLogin
    @DisplayName("Выход из аккаунта")
    @Severity(SeverityLevel.CRITICAL)
    void logoutTest() {
        toolBarPage.openPage("/meetings")
                .clickAvatar()
                .clickLogout()
                .checkSuccessfulLogout();
    }


}
