package tests.api.model.courses;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCoursesRequestModel {
    private Course course;
    private CoursePart coursePart;
    private Lesson lesson;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Course {
        private String name;
        private String startAt;
        private String periodicity;
        private String trajectory;
        private String passingScore;
        private String visibilityStatus;
        private Boolean autoCert;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoursePart {
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Lesson {
        private String name;
        private String lessonType;
    }

    public Map<String, Object> toFormParamsMap() {
        Map<String, Object> formParams = new LinkedHashMap<>();

        // Course parameters
        formParams.put("course[name]", this.course.getName());
        formParams.put("course[startAt]", this.course.getStartAt());
        formParams.put("course[periodicity]", this.course.getPeriodicity());
        formParams.put("course[trajectory]", this.course.getTrajectory());
        formParams.put("course[passingScore]", this.course.getPassingScore());
        formParams.put("course[visibilityStatus]", this.course.getVisibilityStatus());
        formParams.put("course[autoCert]", this.course.getAutoCert());

        // Course part
        formParams.put("coursePart[name]", this.coursePart.getName());

        // Lesson
        formParams.put("lesson[name]", this.lesson.getName());
        formParams.put("lesson[lessonType]", this.lesson.getLessonType());
        return formParams;
    }
}