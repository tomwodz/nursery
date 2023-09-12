package pl.tomwodz.nursery.domain.user;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

@AllArgsConstructor
public class AuthenticationFacade {

    @Resource
    SessionData sessionData;

    private final AuthenticationRepository authenticationRepository;

    public void authenticate(String login, String password) {
        Optional<User> userBox = this.authenticationRepository.findByLogin(login);
        if (userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            userBox.get().setPassword(null);
            this.sessionData.setUser(userBox.get());
        }
    }
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

}
