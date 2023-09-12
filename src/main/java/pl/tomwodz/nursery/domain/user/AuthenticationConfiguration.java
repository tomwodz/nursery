package pl.tomwodz.nursery.domain.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AuthenticationConfiguration {

    @Bean
    AuthenticationFacade authenticationFacade (SessionData sessionData, AuthenticationRepository authenticationRepository){
        return new AuthenticationFacade(sessionData, authenticationRepository);
    }
}
