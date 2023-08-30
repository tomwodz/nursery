package pl.tomwodz.nursery.controllers.rest.dto.response;

import java.util.List;

public record GetAllGroupsChildrenResponseDto(List<GroupChildrenDto> groupsChildren) {
}
