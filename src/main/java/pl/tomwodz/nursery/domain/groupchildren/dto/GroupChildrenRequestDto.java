package pl.tomwodz.nursery.domain.groupchildren.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record GroupChildrenRequestDto(
        @NotNull(message = "{groupChildren.not.null}")
        @Size(min = 3, max = 20, message = "{groupChildren.name.size.3-20}")
        String name
) {
}
