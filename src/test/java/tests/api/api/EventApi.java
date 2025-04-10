package tests.api.api;


import tests.api.model.event.CreateEventRequestModel;
import tests.api.model.event.CreateEventTemplateRequestModel;
import tests.api.model.event.DeleteEventRequestModel;

import static io.restassured.RestAssured.given;
import static tests.api.specs.ApiSpecs.*;

public class EventApi {
    public String getEventId(String sessionId, CreateEventTemplateRequestModel createEventTemplateRequest) {
        return given(baseRequestSpec)
                .cookie("sessionId", sessionId)
                .contentType("application/json")
                .body(createEventTemplateRequest)
                .when()
                .post("/event")
                .then()
                .spec(successResponseSpec)
                .extract().path("id").toString();
    }

    public void createEvent(String sessionId, CreateEventRequestModel createEventRequest, String eventId) {
        given(baseRequestSpec)
                .cookie("sessionId", sessionId)
                .contentType("application/json")
                .body(createEventRequest)
                .when()
                .post("/event/" + eventId + "/session")
                .then()
                .spec(createdResponseSpec);
    }

    public void deleteEvent(String sessionId, DeleteEventRequestModel deleteEventRequest, String eventId) {
        given(baseRequestSpec)
                .cookie("sessionId", sessionId)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(deleteEventRequest.convertToBody())
                .when()
                .delete("/event/" + eventId)
                .then()
                .spec(deleteResponseSpec);
    }
}