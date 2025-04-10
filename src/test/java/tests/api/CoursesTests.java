package tests.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestData;
import tests.api.api.CoursesApi;
import tests.api.api.UserApi;
import tests.api.model.account.LoginRequestModel;
import tests.api.model.courses.CreateCoursesRequestModel;
import tests.api.model.courses.CreateCoursesResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.specs.ApiSpecs.*;

@Epic("Api")
@Feature("Курсы")
@Tag("api")
public class CoursesTests extends TestBase {
    CoursesApi coursesApi = new CoursesApi();
    UserApi userApi = new UserApi();
    TestData testData = new TestData();
    LoginRequestModel loginRequest = new LoginRequestModel(testData.email, testData.password, true);
    String sessionId = userApi.getSessionId(loginRequest);

    @Test
    @DisplayName("Создание нового курса")
    @Severity(SeverityLevel.BLOCKER)
    void createNewCoursesTest() {
        CreateCoursesRequestModel request = new CreateCoursesRequestModel();
        request.setCourse(new CreateCoursesRequestModel.Course(
                "Новый тестовый курс",
                "2025-04-09T20:19:02+03:00",
                "endless",
                "close",
                "0",
                "all",
                false
        ));
        request.setCoursePart(new CreateCoursesRequestModel.CoursePart("Новая часть"));
        request.setLesson(new CreateCoursesRequestModel.Lesson("Новый урок", "lesson"));

        CreateCoursesResponseModel response = step("Создать новый курс", () ->
                given(baseRequestSpec)
                        .cookie("sessionId", sessionId)
                        .contentType("application/x-www-form-urlencoded; charset=utf-8")
                        .formParams(request.toFormParamsMap())  // Используем преобразованные параметры
                        .when()
                        .post("/courseInstances/parts/lessons")
                        .then()
                        .spec(successResponseSpec)
                .extract().as(CreateCoursesResponseModel.class)
        );

        step("Проверить данные в ответе", () -> {
            assertThat(response.getCourseInstance().getCourse().getName()).isEqualTo("Тестовый курс");
        });

    }

    @Test
    @DisplayName("Удаление курса")
    @Severity(SeverityLevel.CRITICAL)
    void deleteCourseTest() {
        long courseId = coursesApi.createCourseAndGetId(sessionId);

        step("Удалить курс с ID: " + courseId, () ->
                coursesApi.deleteCourses(sessionId, courseId)
        );
    }

}
