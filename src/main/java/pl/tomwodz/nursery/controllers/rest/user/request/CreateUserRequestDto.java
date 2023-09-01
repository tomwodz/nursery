package pl.tomwodz.nursery.controllers.rest.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDto(
        @NotNull(message = "login must not be null")
        @Size(min = 3, max = 50, message = "login must must have 3-50 chars")
        String login,
        @NotNull(message = "name must not be null")
        @Size(min = 3, max = 50, message = "name must must have 3-50 chars")
        String password,

        @NotNull(message = "name must not be null")
        @Size(min = 3, max = 50, message = "name must must have 3-50 chars")
        String name,

        @NotNull(message = "surname must not be null")
        @Size(min = 3, max = 50, message = "surname must must have 3-50 chars")
        String surname,

        @NotNull(message = "email must not be null")
        @Size(min = 3, max = 50, message = "email must must have 3-50 chars")
        String emial,

        @NotNull(message = "phone number must not be null")
        @Size(min = 11, max = 11, message = "phone number must must have 11 chars")
        String phonenumber,

        @NotNull(message = "street must not be null")
        @Size(min = 3, max = 50, message = "street must must have 3-24 chars")
        String street,

        @NotNull(message = "zipCode must not be null")
        @Size(min = 6, max = 6, message = "zipCode must must have 6 chars")
        String zipCode,

        @NotNull(message = "city must not be null")
        @Size(min = 3, max = 24, message = "city must must have  3-24 chars")
        String city
) {
}