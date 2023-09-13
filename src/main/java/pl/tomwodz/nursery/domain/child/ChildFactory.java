package pl.tomwodz.nursery.domain.child;

import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.SimpleGroupChildrenQueryDto;
import pl.tomwodz.nursery.domain.user.dto.SimpleUserQueryDto;

class ChildFactory {

    Child mapFromChildRequestDtoToChild(ChildRequestDto dto) {
        return new Child (
                dto.name(),
                dto.surname(),
                new SimpleGroupChildrenQueryDto(dto.groupChildren_id()),
                dto.dayBirth(),
                new SimpleUserQueryDto(dto.user_id()));
    }

}
