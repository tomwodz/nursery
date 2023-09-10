package pl.tomwodz.nursery.domain.address.dto;

import lombok.Builder;

@Builder
public record AddressRequestDto(
        String street,

        String zipCode,

        String city
) {
}
