package pl.tomwodz.nursery.domain.user;

import pl.tomwodz.nursery.domain.user.dto.UserResponseDto;

class UserMapper {
    public static UserResponseDto fromUserToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().toString())
                .active(user.isActive())
                .street(user.getAddress().getStreet())
                .zipCode(user.getAddress().getZipCode())
                .city(user.getAddress().getCity())
                .build();
    }

}
