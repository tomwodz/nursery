package pl.tomwodz.nursery.domain.information;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;

@Configuration
public class InformationConfiguration {

    @Bean
    InformationFacade informationFacade(InformationRepository informationRepository, ValidatorFacade validatorFacade) {
        return new InformationFacade(informationRepository, validatorFacade);
    }
}
