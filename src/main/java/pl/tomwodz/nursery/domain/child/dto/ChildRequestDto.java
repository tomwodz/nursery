package pl.tomwodz.nursery.domain.child.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChildRequestDto(
                              String name,
                              String surname,
                              Long groupChildren_id,
                              LocalDateTime dayBirth,
                              Long user_id) {
}
