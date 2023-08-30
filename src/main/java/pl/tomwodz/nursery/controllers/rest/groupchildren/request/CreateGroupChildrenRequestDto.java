package pl.tomwodz.nursery.controllers.rest.groupchildren.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateGroupChildrenRequestDto(
        @NotNull(message = "name must not be null")
        @Size(min = 3, max = 20, message = "name must must have 3-20 chars")
        String name

) {
}
