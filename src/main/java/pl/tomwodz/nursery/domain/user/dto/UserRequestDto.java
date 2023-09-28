package pl.tomwodz.nursery.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRequestDto(
        @NotNull(message = "{login.not.null}")
        @Size(min = 3, max = 50, message = "{login.size3-50}")
        String login,
        @NotNull(message = "{password.not.null}")
        @Size(min = 3, max = 50, message = "{password.size3-50}")
        String password,

        @NotNull(message = "{name.not.null}")
        @Size(min = 3, max = 50, message = "{name.size3-50}")
        String name,

        @NotNull(message = "{surname.not.null}")
        @Size(min = 3, max = 50, message = "{surname.size3-50}")
        String surname,

        @NotNull(message = "{email.not.null}")
        @Size(min = 3, max = 50, message = "{email.size3-50}")
        String email,

        @NotNull(message = "{phoneNumber.not.null}")
        @Size(min = 11, max = 11, message = "{phoneNumber.size11}")
        String phoneNumber,

        @NotNull(message = "{street.not.null}")
        @Size(min = 11, max = 11, message = "{street.size3-24}")
        String street,

        @NotNull(message = "{zipCode.not.null}")
        @Size(min = 11, max = 11, message = "{zipCode.size6}")
        String zipCode,

        @NotNull(message = "{city.not.null}")
        @Size(min = 11, max = 11, message = "{city.size3-24}")
        String city
) {
}
