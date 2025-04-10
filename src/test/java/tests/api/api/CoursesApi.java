package tests.api.api;

import tests.api.model.courses.CreateCoursesRequestModel;
import tests.api.model.courses.CreateCoursesResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static tests.api.specs.ApiSpecs.*;

public class CoursesApi {

    public Long createCourseAndGetId(String sessionId) {
        CreateCoursesRequestModel request = new CreateCoursesRequestModel();
        request.setCourse(new CreateCoursesRequestModel.Course(
                "Тестовый курс",
                "2025-04-09T20:19:02+03:00",
                "endless",
                "close",
                "0",
                "all",
                false
        ));
        request.setCoursePart(new CreateCoursesRequestModel.CoursePart("Новая часть"));
        request.setLesson(new CreateCoursesRequestModel.Lesson("Новый урок", "lesson"));

        return step("Создать новый курс и получить его ID", () ->
                given(baseRequestSpec)
                        .cookie("sessionId", sessionId)
                        .contentType("application/x-www-form-urlencoded; charset=utf-8")
                        .formParams(request.toFormParamsMap())
                        .when()
                        .post("/courseInstances/parts/lessons")
                        .then()
                        .spec(successResponseSpec)
                        .extract()
                        .jsonPath()
                        .getLong("courseInstance.course.id")
        );
    }

    public void deleteCourses(String sessionId, long courseId) {
        given(baseRequestSpec)
                .cookie("sessionId", sessionId)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .when()
                .delete("/courses/" + courseId)
                .then()
                .spec(deleteResponseSpec);
    }
}
