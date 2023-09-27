package pl.tomwodz.nursery.domain.presence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.child.ChildFacade;

@Configuration
class PresenceConfiguration {

    @Bean
    PresenceFacade presenceFacade (PresenceRepository presenceRepository, ChildFacade childFacade){
        PresenceFactory presenceFactory = new PresenceFactory();
        return new PresenceFacade(presenceRepository, presenceFactory, childFacade);
    }
}
