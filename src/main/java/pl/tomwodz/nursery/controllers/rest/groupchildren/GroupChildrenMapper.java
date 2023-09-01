package pl.tomwodz.nursery.controllers.rest.groupchildren;

import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.controllers.rest.groupchildren.request.CreateGroupChildrenRequestDto;
import pl.tomwodz.nursery.controllers.rest.groupchildren.request.UpdateGroupChildrenRequestDto;
import pl.tomwodz.nursery.controllers.rest.groupchildren.response.*;
import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;

public class GroupChildrenMapper {

    public static GroupChildrenDto mapFromGroupChildrenToGroupChildrenDto(GroupChildren groupChildren) {
        return new GroupChildrenDto(groupChildren.getId(),
                groupChildren.getName());
    }
    public static GetAllGroupsChildrenResponseDto mapFromGroupChildrenToGetAllGroupChildrenResponseDto(List<GroupChildren> groupsChildren) {
        List<GroupChildrenDto> gropusChildrenDtos = groupsChildren.stream()
                .map(GroupChildrenMapper::mapFromGroupChildrenToGroupChildrenDto)
                .toList();
        return new GetAllGroupsChildrenResponseDto(gropusChildrenDtos);
    }

    public static GetGroupChildrenDto mapFromGroupChildrenToGetGroupChildrenDto(GroupChildren groupChildren) {
        GroupChildrenDto groupChildrenDto = GroupChildrenMapper.mapFromGroupChildrenToGroupChildrenDto(groupChildren);
        return new GetGroupChildrenDto(groupChildrenDto);
    }

    public static DeleteGroupChildrenResponseDto mapFromGroupChildrenToDeleteGroupChildrenResponseDto(Long id) {
        return new DeleteGroupChildrenResponseDto("You deleted groupChildren with id: " + id, HttpStatus.OK);
    }

    public static DeleteGroupChildrenResponseDto NotDeleteGroupChildrenWithChild(Long id) {
        return new DeleteGroupChildrenResponseDto(
                "You can not delete groupChildren with have children. GroupChildren id: " + id,
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    public static GroupChildren CreateGroupChildrenRequestDtoToGroupChildren(CreateGroupChildrenRequestDto dto) {
        return new GroupChildren(dto.name());
    }

    public static CreateGroupChildrenResponseDto mapFromGroupChildrenToCreateGroupChildrenResponseDto(GroupChildren groupChildren) {
        GroupChildrenDto groupChildrenDto = GroupChildrenMapper.mapFromGroupChildrenToGroupChildrenDto(groupChildren);
        return new CreateGroupChildrenResponseDto(groupChildrenDto);
    }

    public static GroupChildren mapFromUpdateGroupChildrenRequestDtoToGroupChildren(UpdateGroupChildrenRequestDto dto) {
        String newName = dto.name();
        return new GroupChildren(newName);
    }

    public static UpdateGroupChildrenResponseDto mapFromGroupChildrenToUpdateGroupChildrenResponseDto(GroupChildren newGroupChildren) {
        return new UpdateGroupChildrenResponseDto(newGroupChildren.getName());
    }
}
