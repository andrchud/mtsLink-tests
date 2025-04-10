package pages.web;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.api.api.EventApi;
import tests.api.model.event.DeleteEventRequestModel;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class EventPage extends PageBase {

    EventApi eventApi = new EventApi();

    private final SelenideElement
            startFastMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.startMeeting")),
            scheduleButton = $(byAttribute("data-testid", "Meetings.Toolbar.schedule")),
            scheduleMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.scheduleMeeting")),
            scheduleEndlessMeetingButton = $(byAttribute("data-testid", "Meetings.Toolbar.scheduleEndlessMeeting")),
            goToEventButton = $(byAttribute("data-testid", "MeetingsEditor.Topbar.goToEvent")),
            startMeetingButton = $(byText("Начать встречу")),
            nameEventInput = $(byAttribute("data-testid", "MeetingsEditor.Hero.Body.Title.textarea")),
            errorNameEventMessage = $(byText("Название не может быть пустым")),
            publisher = $(byAttribute("data-testid", "PrepareVCS.microphone.select")),
            joinMeetingButton = $(byText("Присоединиться к встрече")),
            vcs = $(byAttribute("data-testid", "StreamTopBar.topbarButton.reportIssue")),
            redirectChatButton = $(byAttribute("data-testid", "Meetings.Item.courses"));

    @Step("Открыть страницу /meetings")
    public EventPage openPage() {
        open("/meetings");
        return this;
    }

    @Step("Нажать кнопку Быстрая встреча")
    public EventPage clickStartFastMeetingButton() {
        startFastMeetingButton.click();
        return this;
    }

    @Step("Нажать кнопку Запланировать")
    public EventPage clickScheduleButton() {
        scheduleButton.click();
        return this;
    }

    @Step("Нажать кнопку Встречу")
    public EventPage clickScheduleMeetingButton() {
        scheduleMeetingButton.click();
        return this;
    }

    @Step("Нажать кнопку Постоянную встречу")
    public EventPage clickScheduleEndlessMeetingButton() {
        scheduleEndlessMeetingButton.click();
        return this;
    }

    @Step("Нажать кнопку Перейти к встрече")
    public EventPage clickGoToEventButton() {
        goToEventButton.click();
        return this;
    }

    @Step("Нажать кнопку Начать встречу")
    public EventPage clickStartMeetingButtonButton() {
        startMeetingButton.click();
        return this;
    }

    @Step("Очистить название мероприятия")
    public EventPage clearNameEventInput() {
        cleanInput(nameEventInput);
        return this;
    }

    @Step("Проверить отображение ошибки: Название не может быть пустым")
    public EventPage checkErrorNameEventMessage() {
        errorNameEventMessage.shouldHave(visible);
        return this;
    }


    @Step("Проверить отображение паблишера")
    public EventPage checkPublisherVisible() {
        publisher.shouldHave(visible);
        return this;
    }

    @Step("Нажать кнопку Присоединиться к встрече")
    public EventPage clickJoinMeetingButton() {
        joinMeetingButton.click();
        return this;
    }

    @Step("Проверить отображение своей ВКС")
    public EventPage checkVCSVisible() {
        vcs.shouldHave(visible);
        return this;
    }

    @Step("Нажать кнопку Курсы")
    public EventPage clickRedirectCourseButton() {
        redirectChatButton.click();
        return this;
    }

    @Step("Удалить мероприятие")
    public void deleteEvent(String sessionId, DeleteEventRequestModel deleteEventRequest, String eventId) {
        eventApi.deleteEvent(sessionId, deleteEventRequest, eventId);
    }
}