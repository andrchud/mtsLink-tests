package tests.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestData;
import tests.api.api.EventApi;
import tests.api.api.UserApi;
import tests.api.model.account.LoginRequestModel;
import tests.api.model.event.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.api.specs.ApiSpecs.requestSpec;
import static tests.api.specs.ApiSpecs.responseSpec;

@Epic("Api")
@Feature("Мероприятия")
@Tag("api")
public class EventTests extends TestBase {

    EventApi eventApi = new EventApi();
    UserApi userApi = new UserApi();
    TestData testData = new TestData();
    LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);
    String sessionId = userApi.getSessionId(loginRequest);

    @Test
    @DisplayName("Создание шаблона для мероприятия")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTemplateTest() {
        CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
        CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);

        CreateEventTemplateResponseModel response = step("Создать шаблон для мероприятия", () ->
                given(requestSpec)
                        .cookie("sessionId", sessionId)
                        .contentType("application/json")
                        .body(createEventTemplateRequest)
                        .when()
                        .post("/event")
                        .then()
                        .spec(responseSpec)
                        .statusCode(201))
                .extract().as(CreateEventTemplateResponseModel.class);

        step("Проверить данные в ответе", () -> {
            assertEquals(testData.eventName, response.getName());
            assertEquals("meeting", response.getType());
        });
    }

    @Test
    @DisplayName("Создание быстрой встречи")
    @Severity(SeverityLevel.BLOCKER)
    void createEventTest() {
        CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
        CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel(testData.eventName, accessSettings);
        CreateEventRequestModel createEventRequest = new CreateEventRequestModel("2023-11-02T11:06:04+0300", 100, true, "google_calendar", accessSettings);
        DeleteEventRequestModel deleteEventRequest = new DeleteEventRequestModel(false);

        String eventId = step("Создать шаблон для быстрой встречи", () ->
                eventApi.getEventId(sessionId, createEventTemplateRequest));

        CreateEventResponseModel response = step("Создать быструю встречу", () ->
                given(requestSpec)
                        .cookie("sessionId", sessionId)
                        .contentType("application/json")
                        .body(createEventRequest)
                        .when()
                        .post("/event/" + eventId + "/session")
                        .then()
                        .spec(responseSpec)
                        .statusCode(201))
                .extract().as(CreateEventResponseModel.class);

        step("Проверить данные в ответе", () -> {
            assertEquals(testData.eventName, response.getName());
            assertEquals("meeting", response.getType());
            assertEquals("ACTIVE", response.getStatus());
        });

        step("Удалить быструю встречу", () ->
                eventApi.deleteEvent(sessionId, deleteEventRequest, eventId));
    }

    @Test
    @DisplayName("Удаление мероприятия")
    @Severity(SeverityLevel.CRITICAL)
    void deleteEvent() {
        CreateEventTemplateRequestModel.AccessSettingsModel accessSettings = new CreateEventTemplateRequestModel.AccessSettingsModel(false, false, false);
        CreateEventTemplateRequestModel createEventTemplateRequest = new CreateEventTemplateRequestModel("New Event", accessSettings);
        CreateEventRequestModel createEventRequest = new CreateEventRequestModel("2023-11-02T11:06:04+0300", 100, true, "google_calendar", accessSettings);
        DeleteEventRequestModel deleteEventRequest = new DeleteEventRequestModel(false);

        String eventId = step("Создать шаблон для мероприятия", () ->
                eventApi.getEventId(sessionId, createEventTemplateRequest));

        step("Создать мероприятие", () ->
                eventApi.createEvent(sessionId, createEventRequest, eventId));

        step("Удалить мероприятие", () ->
                eventApi.deleteEvent(sessionId, deleteEventRequest, eventId));
    }
}