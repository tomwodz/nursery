package pl.tomwodz.nursery.controllers.rest.user.response;

public record UserDtoWithoutLogin(
        Long id,
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
