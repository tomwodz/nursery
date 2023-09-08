package pl.tomwodz.nursery.domain.groupchildren.dto;

import lombok.Builder;

@Builder
public record GroupChildrenResponseDto(
        Long id,
        String name) {
}
