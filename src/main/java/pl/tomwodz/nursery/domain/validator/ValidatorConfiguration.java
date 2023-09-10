package pl.tomwodz.nursery.domain.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfiguration {

    @Bean
    ValidatorFacade validatorFacade() {
        ValidatorInformation validatorInformation = new ValidatorInformation();
        ValidatorGroupChildren validatorGroupChildren = new ValidatorGroupChildren();
        ValidatorChild validatorChild = new ValidatorChild();
        return new ValidatorFacade(validatorInformation, validatorGroupChildren, validatorChild);
    }


}
