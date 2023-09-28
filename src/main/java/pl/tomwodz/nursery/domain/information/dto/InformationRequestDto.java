package pl.tomwodz.nursery.domain.information.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record InformationRequestDto(
        Long author_id,
        @NotNull(message = "{title.not.null}")
        @Size(min = 3, max = 255, message = "{title.size3-255}")
        String title,

        @NotNull(message = "{content.not.null}")
        @Size(min = 3, max = 255, message = "{content.size3-255}")
        String content
) {
}
