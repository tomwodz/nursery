package pl.tomwodz.nursery.domain.groupchildren.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record GroupChildrenRequestDto(
        @NotNull(message = "name must not be null")
        @Size(min = 3, max = 20, message = "name must must have 3-20 chars")
        String name
) {
}
