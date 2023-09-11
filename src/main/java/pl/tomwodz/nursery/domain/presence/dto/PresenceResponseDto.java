package pl.tomwodz.nursery.domain.presence.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PresenceResponseDto(
        Long id,

        Long child_id,

        LocalDateTime dataTimeEntry,

        LocalDateTime dataTimeDeparture

) {
}
