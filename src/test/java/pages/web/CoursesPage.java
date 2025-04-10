package pages.web;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.api.api.CoursesApi;
import tests.api.model.event.DeleteEventRequestModel;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class CoursesPage extends PageBase{

    CoursesApi coursesApi = new CoursesApi();

    private final SelenideElement
            createCourseButton = $("[class*='CreateCourseCard__content']"),
            notTemplateCourseButton = $(byText("Создать новый курс")),
            menuButton = $(byAttribute("data-testid", "OptionsMenu.menu")),
            removeCourseButton = $(byAttribute("data-testid", "CourseContextMenu.Item.remove")),
            editCourseButton = $(byAttribute("data-testid", "FixedHeader.action.edit")),
            createLessonButton = $(byText("Создать")),
            contextItemLessonButton = $(byAttribute("data-testid", "CourseContextMenu.Item.lesson")),
            newsTabButton = $(byAttribute("data-testid", "CourseInfoHeader.TabBar.newsfeed")),
            createNewsButton = $(byText("Написать")),
            newsTextBox = $(byId("postText")),
            publishButton = $(byAttribute("data-testid", "FixedHeader.action.publish")),
            sendNewsButton = $(byText("Отправить"));

    @Step("Открыть страницу /courses")
    public CoursesPage openPage() {
        open("/courses");
        return this;
    }

    @Step("Нажать кнопку создания нового курса")
    public CoursesPage createCourseButton() {
        createCourseButton.click();
        return this;
    }

    @Step("Выбор курса без шаблона")
    public CoursesPage clickNotTemplateCourseButton() {
        notTemplateCourseButton.click();
        return this;
    }

    @Step("Нажать кнопку Меню курсов")
    public CoursesPage clickMenuButton() {
        menuButton.click();
        return this;
    }

    @Step("Нажать кнопку удаления Курса")
    public CoursesPage clickRemoveCourseButton() {
        removeCourseButton.click();
        return this;
    }

    @Step("Нажать кнопку Редактировать")
    public CoursesPage clickEditCourseButton() {
        editCourseButton.click();
        return this;
    }

    @Step("Нажать кнопку Создать")
    public CoursesPage clickCreateLessonButton() {
        createLessonButton.click();
        return this;
    }

    @Step("Нажать кнопку Урок в контекстом меню")
    public CoursesPage clickContextItemLessonButton() {
        contextItemLessonButton.click();
        return this;
    }

    @Step("Нажать кнопку Лента в тулл баре")
    public CoursesPage clickNewsTabButton() {
        newsTabButton.click();
        return this;
    }

    @Step("Нажать кнопку Написать")
    public CoursesPage clickCreateNewsButton() {
        createNewsButton.click();
        return this;
    }

    @Step("Ввод текста в текст бокс")
    public CoursesPage setNewsTextBox(String newsText) {
        newsTextBox.sendKeys(newsText);
        return this;
    }

    @Step("Нажать кнопку Отправить")
    public CoursesPage clickSendNewsButton() {
        sendNewsButton.click();
        return this;
    }

    @Step("Проверить редирект на страницу Курсов")
    public CoursesPage checkCoursesPageVisible() {
        assert webdriver().object().getCurrentUrl().contains("/courses");
        return this;
    }

    @Step("Проверить редирект на страницу созданного курса")
    public CoursesPage checkLoadCoursesPageAfterCreate() {
        publishButton.shouldHave(visible);
        return this;
    }

    @Step("Удалить курс")
    public void deleteCourse(String sessionId, long courseId) {
        coursesApi.deleteCourses(sessionId, courseId);
    }
}
