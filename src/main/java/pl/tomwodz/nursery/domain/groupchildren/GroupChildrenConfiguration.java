package pl.tomwodz.nursery.domain.groupchildren;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;

@Configuration
class GroupChildrenConfiguration {

    @Bean
    GroupChildrenFacade groupChildrenFacade(GroupChildrenRepository groupChildrenRepository,
                                            ValidatorFacade validatorFacade,
                                            ChildFacade childFacade) {
        GroupChildrenFactory groupChildrenFactory = new GroupChildrenFactory();
        return new GroupChildrenFacade(groupChildrenRepository,
                validatorFacade,
                groupChildrenFactory,
                childFacade);
    }

}
