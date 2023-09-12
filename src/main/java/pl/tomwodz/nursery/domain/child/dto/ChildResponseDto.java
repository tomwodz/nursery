package pl.tomwodz.nursery.domain.child.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChildResponseDto(Long id,
                               String name,
                               String surname,
                               Long groupChildren_id,
                               LocalDateTime dayBirth,
                               Long user_id) {
}
