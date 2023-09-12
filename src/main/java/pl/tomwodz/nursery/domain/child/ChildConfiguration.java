package pl.tomwodz.nursery.domain.child;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;

@Configuration
class ChildConfiguration {

    @Bean
    ChildFacade childFacade(ChildRepository childRepository, ValidatorFacade validatorFacade){
        ChildFactory childFactory = new ChildFactory();
        return new ChildFacade(childRepository, validatorFacade, childFactory);
    }
}
