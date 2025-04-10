package tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.web.AuthorizationPage;
import pages.web.ProfilePage;
import pages.web.ToolBarPage;
import tests.TestData;
import tests.api.extensions.WithLogin;


@Epic("Web")
@Feature("Авторизация")
@Tag("web")
public class AccountTests extends TestBase {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    TestData testData = new TestData();
    ToolBarPage toolBarPage = new ToolBarPage();
    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Авторизация по почте и паролю")
    @Severity(SeverityLevel.BLOCKER)
    void authorizationTest() {
        authorizationPage.openPage()
                .setEmail(testData.email)
                .setPassword(testData.password)
                .clickSubmit()
                .checkSuccessfulAuthorization();
    }

    @Test
    @WithLogin
    @DisplayName("Изменение информации в профиле")
    @Severity(SeverityLevel.CRITICAL)
    void changeProfileDataTest() {
        toolBarPage.openPage()
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
    @Severity(SeverityLevel.BLOCKER)
    void logoutTest() {
        toolBarPage.openPage()
                .clickAvatar()
                .clickLogout()
                .checkSuccessfulLogout();
    }


}
