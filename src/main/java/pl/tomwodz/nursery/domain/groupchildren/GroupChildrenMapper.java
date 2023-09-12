package pl.tomwodz.nursery.domain.groupchildren;

import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;

class GroupChildrenMapper {

    public static GroupChildrenResponseDto mapFromGroupChildrenToGroupChildrenResponseDto(GroupChildren groupChildren) {
        return GroupChildrenResponseDto.builder()
                .id(groupChildren.getId())
                .name(groupChildren.getName())
                .build();
    }

}
