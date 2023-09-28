package pl.tomwodz.nursery.domain.child.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChildRequestDto(
        @NotNull(message = "{name.not.null}")
        String name,
        @NotNull(message = "{surname.not.null}")
        String surname,
        @NotNull(message = "{groupChildren_id.not.null}")
        Long groupChildren_id,
        @NotNull(message = "{dayBirth.not.null}")
        LocalDateTime dayBirth,
        @NotNull(message = "{user_id.not.null}")
        Long user_id) {
}
