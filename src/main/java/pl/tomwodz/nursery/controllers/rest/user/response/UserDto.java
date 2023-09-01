package pl.tomwodz.nursery.controllers.rest.user.response;

public record UserDto(
        Long id,
        String login,
        String name,
        String email,
        String phoneNumber,
        String street,
        String zipCode,
        String city,
        String role,
        boolean active

        ) {
}

