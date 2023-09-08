package pl.tomwodz.nursery.domain.groupchildren;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupChildrenConfiguration {

    @Bean
    GroupChildrenFacade groupChildrenFacade(GroupChildrenRepository groupChildrenRepository){
        return new GroupChildrenFacade(groupChildrenRepository);
    }
}
