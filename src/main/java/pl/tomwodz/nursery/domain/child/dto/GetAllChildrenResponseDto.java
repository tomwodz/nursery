package pl.tomwodz.nursery.domain.child.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GetAllChildrenResponseDto(List<ChildResponseDto> children) {
}
