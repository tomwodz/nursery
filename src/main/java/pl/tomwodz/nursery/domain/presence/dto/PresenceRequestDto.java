package pl.tomwodz.nursery.domain.presence.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PresenceRequestDto(
        Long id,

        LocalDateTime dataTimeEntry,

        LocalDateTime dataTimeDeparture
) {
}
