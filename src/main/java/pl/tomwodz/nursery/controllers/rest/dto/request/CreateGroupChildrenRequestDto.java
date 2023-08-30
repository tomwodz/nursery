package pl.tomwodz.nursery.controllers.rest.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateGroupChildrenRequestDto(
        @NotNull(message = "name must not be null")
        @NotEmpty(message = "name must not be empty")
        String name

) {
}
