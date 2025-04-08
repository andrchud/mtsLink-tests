package tests.api.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestModel {
    private String email, password;
    Boolean rememberMe;

    public String convertToBody() {
        return "email=" + email + "&password=" + password + "&rememberMe=" + rememberMe + "&strictDomainAccess=true" + "&host=my.mts-link.ru";
    }
}
