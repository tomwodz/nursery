package pl.tomwodz.nursery.domain.groupchildren;

import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.model.GroupChildren;

public class GroupChildrenMapper {

    public static GroupChildrenResponseDto mapFromGroupChildrenToGroupChildrenResponseDto(GroupChildren groupChildren) {
        return GroupChildrenResponseDto.builder()
                .id(groupChildren.getId())
                .name(groupChildren.getName())
                .build();
    }

    public static GroupChildren mapFromGroupChildrenRequestDtoToGroupChildren(GroupChildrenRequestDto requestDto) {
        return GroupChildren.builder()
                .name(requestDto.name())
                .build();
    }

    public static GroupChildren mapFromUpdateGroupChildrenRequestDtoToGroupChildren(Long id, GroupChildrenRequestDto requestDto) {
        return GroupChildren.builder()
                .name(requestDto.name())
                .id(id)
                .build();
    }
}
