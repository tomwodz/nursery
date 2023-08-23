package pl.tomwodz.nursery.services;

import jakarta.servlet.http.HttpServletRequest;
import pl.tomwodz.nursery.model.User;

public interface AuthenticationService {
    void authenticate(String login, String password);

    void logout(HttpServletRequest request);

}
