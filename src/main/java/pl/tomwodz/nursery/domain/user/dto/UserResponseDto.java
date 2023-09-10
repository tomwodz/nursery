package pl.tomwodz.nursery.domain.user.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
                            Long id,
                              String login,
                              String name,

                              String surname,
                              String email,
                              String phoneNumber,
                              String street,
                              String zipCode,
                              String city,
                              String role,
                              boolean active) {
}
