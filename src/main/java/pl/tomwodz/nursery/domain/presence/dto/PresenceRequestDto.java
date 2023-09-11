package pl.tomwodz.nursery.domain.presence.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record PresenceRequestDto(
        Long child_id,

        LocalDateTime dataTimeEntry,

        LocalDateTime dataTimeDeparture
) {
}
