package pl.tomwodz.nursery.domain.user;

import org.apache.commons.codec.digest.DigestUtils;
import pl.tomwodz.nursery.domain.address.Address;
import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserResponseDto;

public class UserMapper {
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

    public static User mapFromUserRequestDtoToUser(UserRequestDto requestDto) {
        return User.builder()
                .login(requestDto.login())
                .password(DigestUtils.md5Hex(requestDto.password()))
                .name(requestDto.name())
                .surname(requestDto.surname())
                .email(requestDto.emial())
                .phoneNumber(requestDto.phonenumber())
                .address(Address.builder()
                        .street(requestDto.street())
                        .zipCode(requestDto.zipCode())
                        .city(requestDto.city())
                        .build())
                .build();
    }

    public static User mapFromUpdateUserRequestDtoToUser(Long id, UpdateUserRequestDto requestDto) {
        return User.builder()
                .id(id)
                .name(requestDto.name())
                .surname(requestDto.surname())
                .email(requestDto.emial())
                .phoneNumber(requestDto.phonenumber())
                .address(Address
                        .builder()
                        .id(id)
                        .street(requestDto.street())
                        .zipCode(requestDto.zipCode())
                        .city(requestDto.city())
                        .build())
                .build();
    }
}
