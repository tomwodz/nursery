package pl.tomwodz.nursery.domain.information;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;

@Configuration
class InformationConfiguration {

    @Bean
    InformationFacade informationFacade(InformationRepository informationRepository,
                                        ValidatorFacade validatorFacade) {
        InformationFactory informationFactory = new InformationFactory();
        return new InformationFacade(informationRepository, validatorFacade, informationFactory);
    }
}
