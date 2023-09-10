package pl.tomwodz.nursery.domain.child;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;

@Configuration
public class ChildConfiguration {

    @Bean
    ChildFacade childFacade(ChildRepository childRepository, ValidatorFacade validatorFacade){
        return new ChildFacade(childRepository, validatorFacade);
    }
}
