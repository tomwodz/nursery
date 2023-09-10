package pl.tomwodz.nursery.domain.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.infrastructure.session.SessionData;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    AuthenticationFacade authenticationFacade (SessionData sessionData, AuthenticationRepository authenticationRepository){
        return new AuthenticationFacade(sessionData, authenticationRepository);
    }
}
