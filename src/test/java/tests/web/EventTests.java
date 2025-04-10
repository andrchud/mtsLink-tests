package tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.web.EventPage;
import tests.TestData;
import tests.api.api.UserApi;
import tests.api.extensions.WithLogin;
import tests.api.model.account.LoginRequestModel;
import tests.api.model.event.DeleteEventRequestModel;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.Helpers.getEventIdFromUrl;

@Epic("Web")
@Feature("Быстрая встреча")
@Tag("web")
public class EventTests extends TestBase{
    UserApi userApi = new UserApi();
    TestData testData = new TestData();
    LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);
    String sessionId = userApi.getSessionId(loginRequest);

    EventPage eventPage = new EventPage();

    @Test
    @WithLogin
    @DisplayName("Запуск быстрой встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createFastMeetingTest() {
        eventPage.openPage()
                .clickStartFastMeetingButton()
                .checkPublisherVisible()
                .clickJoinMeetingButton()
                .checkVCSVisible()
                .deleteEvent(sessionId, new DeleteEventRequestModel(false), getEventIdFromUrl(getWebDriver().getCurrentUrl()));
    }

    @Test
    @WithLogin
    @DisplayName("Запуск запланированной встречи")
    @Severity(SeverityLevel.CRITICAL)
    void  createScheduleMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .clickGoToEventButton()
                .clickStartMeetingButtonButton()
                .checkPublisherVisible()
                .clickJoinMeetingButton()
                .checkVCSVisible()
                .deleteEvent(sessionId, new DeleteEventRequestModel(false), getEventIdFromUrl(getWebDriver().getCurrentUrl()));
    }

    @Test
    @WithLogin
    @DisplayName("Запуск постоянной встречи")
    @Severity(SeverityLevel.CRITICAL)
    void  createScheduleEndlessMeetingTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleEndlessMeetingButton()
                .clickGoToEventButton()
                .checkPublisherVisible()
                .clickJoinMeetingButton()
                .checkVCSVisible()
                .deleteEvent(sessionId, new DeleteEventRequestModel(false), getEventIdFromUrl(getWebDriver().getCurrentUrl()));
    }

    @Test
    @WithLogin
    @DisplayName("Нельзя создать встречу без названия")
    @Severity(SeverityLevel.CRITICAL)
    void  errorCreateMeetingWithOutNameTest() {
        eventPage.openPage()
                .clickScheduleButton()
                .clickScheduleMeetingButton()
                .clearNameEventInput()
                .clickGoToEventButton()
                .checkErrorNameEventMessage();
    }
}
