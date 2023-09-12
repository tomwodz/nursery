package pl.tomwodz.nursery.domain.presence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PresenceConfiguration {

    @Bean
    PresenceFacade presenceFacade (PresenceRepository presenceRepository){
        return new PresenceFacade(presenceRepository);
    }
}
