package pl.tomwodz.nursery.domain.child.dto;

import lombok.Builder;

@Builder
public record ChildRequestDto(
                              String name,
                              String surname,
                              Long groupChildren_id,
                              String dayBirth,
                              Long user_id) {
}
