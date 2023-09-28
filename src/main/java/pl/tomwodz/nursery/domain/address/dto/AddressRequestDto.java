package pl.tomwodz.nursery.domain.address.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AddressRequestDto(
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
