package tests.api.model.courses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCoursesResponseModel {
    private CourseInstance courseInstance;
    private CoursePartResponse coursePart;
    private LessonResponse lesson;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CourseInstance {
        private Long id;
        private String status;
        private String startAt;
        private String endAt;
        private CourseDetails course;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CourseDetails {
        private String visibilityStatus;
        private String description;
        private String trajectory;
        private String passingScore;
        private String certSetting;
        private String urlAlias;
        private String periodicity;
        private Integer access;
        private String background;
        private User user;
        private Organization organization;
        private String locale;
        private Long id;
        private String status;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String email;
        private String secondName;
        private String nickName;
        private String phone;
        private Long id;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Organization {
        private Long id;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CoursePartResponse {
        private Long id;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LessonResponse {
        private Long lessonId;
        private String lessonType;
        private String name;
        private String text;
        private String accessType;
        private String accessAt;
        private String accessTimeZone;
    }

    public String getCourseName() {
        return courseInstance != null && courseInstance.getCourse() != null
                ? courseInstance.getCourse().getName()
                : null;
    }

    public Long getCourseId() {
        return courseInstance != null && courseInstance.getCourse() != null
                ? courseInstance.getCourse().getId()
                : null;
    }

    public Long getLessonId() {
        return lesson != null ? lesson.getLessonId() : null;
    }
}