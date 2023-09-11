package pl.tomwodz.nursery.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateUserRequestDto(

        @NotNull(message = "name must not be null")
        @Size(min = 3, max = 50, message = "name must must have 3-50 chars")
        String name,

        @NotNull(message = "surname must not be null")
        @Size(min = 3, max = 50, message = "surname must must have 3-50 chars")
        String surname,

        @NotNull(message = "email must not be null")
        @Size(min = 3, max = 50, message = "email must must have 3-50 chars")
        String email,

        @NotNull(message = "phone number must not be null")
        @Size(min = 11, max = 11, message = "phone number must must have 11 chars")
        String phoneNumber,

        @NotNull(message = "street must not be null")
        @Size(min = 3, max = 50, message = "street must must have 3-24 chars")
        String street,

        @NotNull(message = "zipCode must not be null")
        @Size(min = 6, max = 6, message = "zipCode must must have 6 chars")
        String zipCode,

        @NotNull(message = "city must not be null")
        @Size(min = 3, max = 24, message = "city must must have  3-24 chars")
        String city) {
}
