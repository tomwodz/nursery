package pl.tomwodz.nursery.domain.presence.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PresenceRequestDto(

        @NotNull(message = "{id.not.null}")
        Long id,

        @NotNull(message = "{dataTimeEntry.not.null}")
        LocalDateTime dataTimeEntry,

        @NotNull(message = "{dataTimeDeparture.not.null}")
        LocalDateTime dataTimeDeparture
) {
}
