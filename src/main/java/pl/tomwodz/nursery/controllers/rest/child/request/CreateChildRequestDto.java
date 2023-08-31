package pl.tomwodz.nursery.controllers.rest.child.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChildRequestDto(
        @NotNull(message = "name must not be null")
        @Size(min = 3, max = 50, message = "name must have 3-50 chars")
        String name,

        @NotNull(message = "surname must not be null")
        @Size(min = 3, max = 50, message = "surname must have 3-50 chars")
        String surname,

        @NotNull(message = "dayBrith must not be null")
        @Size(min = 10, max = 10, message = "dayBirth must have format: yyyy-MM-dd")
        String dayBirth

) {
}
