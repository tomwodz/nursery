package pl.tomwodz.nursery.controllers.rest.child.response;

import pl.tomwodz.nursery.controllers.rest.child.response.ChildDto;

import java.util.List;

public record GetAllChildrenResponseDto(List<ChildDto> children) {
}
