package pl.tomwodz.nursery.controllers.rest.information.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UpdateInformationResponseDto(

        Long id,
        Long author_id,
        @NotNull(message = "title must not be null")
        @Size(min = 3, max = 255, message = "title must have 3-255 chars")
        String title,

        @NotNull(message = "content must not be null")
        @Size(min = 3, max = 255, message = "content must must have 3-255 chars")
        String content,

        String dateCreation) {
}
