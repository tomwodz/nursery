package pl.tomwodz.nursery.domain.groupchildren;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;

@Configuration
public class GroupChildrenConfiguration {

    @Bean
    GroupChildrenFacade groupChildrenFacade(GroupChildrenRepository groupChildrenRepository, ValidatorFacade validatorFacade){
        return new GroupChildrenFacade(groupChildrenRepository, validatorFacade);
    }
}
