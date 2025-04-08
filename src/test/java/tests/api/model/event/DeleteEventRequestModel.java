package tests.api.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteEventRequestModel {
    Boolean sendEmail;

    public String convertToBody() {
        return "sendEmail=" + sendEmail;
    }
}
