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
        ValidatorUser validatorUser = new ValidatorUser();
        ValidatorAddress validatorAddress = new ValidatorAddress();
        return new ValidatorFacade(validatorInformation,
                validatorGroupChildren,
                validatorChild,
                validatorUser,
                validatorAddress);
    }


}
