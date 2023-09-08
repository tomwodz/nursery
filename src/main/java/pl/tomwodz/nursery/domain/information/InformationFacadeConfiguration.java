package pl.tomwodz.nursery.domain.information;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InformationFacadeConfiguration {

    @Bean
    InformationFacade informationFacade(InformationRepository informationRepository){
        return new InformationFacade(informationRepository);
    }
}
