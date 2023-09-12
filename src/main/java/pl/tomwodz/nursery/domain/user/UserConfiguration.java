package pl.tomwodz.nursery.domain.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;

@Configuration
class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository, ValidatorFacade validatorFacade){
        return new UserFacade(userRepository, validatorFacade);
    }
}
