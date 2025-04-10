package tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.web.CoursesPage;
import pages.web.EventPage;
import tests.TestData;
import tests.api.api.UserApi;
import tests.api.extensions.WithLogin;
import tests.api.model.account.LoginRequestModel;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.Helpers.getCourseIdFromUrl;


@Epic("Web")
@Feature("Курсы")
@Tag("web")
public class CoursesTests extends TestBase{
    UserApi userApi = new UserApi();
    TestData testData = new TestData();
    EventPage eventPage = new EventPage();
    CoursesPage coursesPage = new CoursesPage();
    LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);
    String sessionId = userApi.getSessionId(loginRequest);

    @Test
    @WithLogin
    @DisplayName("Переход в Курсы с страницы мероприятий")
    @Severity(SeverityLevel.BLOCKER)
    void redirectToChatsTest() {
        eventPage.openPage()
                .clickRedirectCourseButton();

        coursesPage.checkCoursesPageVisible();

    }

    @Test
    @WithLogin
    @DisplayName("Создание нового курса")
    @Severity(SeverityLevel.BLOCKER)
    void createNewCommandTest() {
        coursesPage.openPage()
                .createCourseButton()
                .clickNotTemplateCourseButton()
                .checkLoadCoursesPageAfterCreate()
                .deleteCourse(sessionId,getCourseIdFromUrl(getWebDriver().getCurrentUrl()));

    }

    @Test
    @WithLogin
    @DisplayName("Удаление курса")
    @Severity(SeverityLevel.CRITICAL)
    void createNewChannelTest() {
        coursesPage.openPage().
                createCourseButton().
                clickNotTemplateCourseButton()
                .clickMenuButton()
                .clickRemoveCourseButton();
    }

    @Test
    @WithLogin
    @DisplayName("Добавить урок")
    @Severity(SeverityLevel.BLOCKER)
    void sendMessageTest() {
        coursesPage.openPage().
                createCourseButton().
                clickNotTemplateCourseButton()
                .clickEditCourseButton()
                .clickCreateLessonButton()
                .clickContextItemLessonButton()
                .deleteCourse(sessionId,getCourseIdFromUrl(getWebDriver().getCurrentUrl()));
    }

}
