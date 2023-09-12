package pl.tomwodz.nursery.domain.presence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PresenceConfiguration {

    @Bean
    PresenceFacade presenceFacade (PresenceRepository presenceRepository){
        PresenceFactory presenceFactory = new PresenceFactory();
        return new PresenceFacade(presenceRepository, presenceFactory);
    }
}
