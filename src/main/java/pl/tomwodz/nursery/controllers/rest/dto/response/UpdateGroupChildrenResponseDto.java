package pl.tomwodz.nursery.controllers.rest.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateGroupChildrenResponseDto(
        @NotNull(message = "name must not be null")
        @NotEmpty(message = "name must not be empty")
        String name
) {
}
