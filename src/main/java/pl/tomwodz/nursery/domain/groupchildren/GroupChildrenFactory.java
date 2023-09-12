package pl.tomwodz.nursery.domain.groupchildren;

import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;

class GroupChildrenFactory {


    GroupChildren mapFromGroupChildrenRequestDtoToGroupChildren(GroupChildrenRequestDto requestDto) {
        return new GroupChildren(requestDto.name());
    }

    GroupChildren mapFromUpdateGroupChildrenRequestDtoToGroupChildren(Long id, GroupChildrenRequestDto requestDto) {
        return new GroupChildren(id, requestDto.name());
    }

}
