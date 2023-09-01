package pl.tomwodz.nursery.controllers.rest.user;

import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.controllers.rest.user.response.UpdateUserResponseDto;
import pl.tomwodz.nursery.controllers.rest.user.request.CreateUserRequestDto;
import pl.tomwodz.nursery.controllers.rest.user.request.UpdateUserRequestDto;
import pl.tomwodz.nursery.controllers.rest.user.response.*;
import pl.tomwodz.nursery.model.Address;
import pl.tomwodz.nursery.model.User;

import java.util.List;

public class UserMapper {

    public static UserDto mapFromUserToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress().getStreet(),
                user.getAddress().getZipCode(),
                user.getAddress().getCity(),
                user.getRole().toString(),
                user.isActive()
        );
    }
    public static GetUserDto mapFromUserToGetUserDto(User user) {
        UserDto userDto = mapFromUserToUserDto(user);
        return new GetUserDto(userDto);
    }

    public static GetAllUsersResponseDto mapFroUsersToGetAllUsersResponseDto(List<User> allUsers) {
        List<UserDto> usersDto = allUsers.stream()
                .map(UserMapper::mapFromUserToUserDto)
                .toList();
        return new GetAllUsersResponseDto(usersDto);
    }

    public static DeleteUserResponseDto mapFromUserToDeleteUserResponseDto(Long id) {
        return new DeleteUserResponseDto("You deleted user with id: " + id, HttpStatus.OK);
    }

    public static DeleteUserResponseDto NotDeleteUserWithHaveChildren(Long id) {
        return new DeleteUserResponseDto("You can not delete user with have children. User id: " + id,
                HttpStatus.METHOD_NOT_ALLOWED);
    }


    public static User mapFromCreateUserRequestDtoToUser(CreateUserRequestDto dto) {
        User user = new User();
        user.setLogin(dto.login());
        user.setPassword(dto.password());
        user.setName(dto.name());
        user.setSurname(dto.surname());
        user.setEmail(dto.emial());
        user.setPhoneNumber(dto.phonenumber());
        user.setAddress(new Address(
                dto.street(),
                dto.zipCode(),
                dto.city()));
        return user;
    }

    public static CreateUserResponseDto mapFromUserToCreateUserResponseDto(User savedUser) {
        UserDto userDto = mapFromUserToUserDto(savedUser);
        return new CreateUserResponseDto(userDto);
    }

    public static User mapFromUpdateUserRequestDtoToUser(UpdateUserRequestDto dto) {
        String newName = dto.name();
        String newSurname = dto.surname();
        String newEmail = dto.emial();
        String newPhoneNumber = dto.phonenumber();
        String newStreet =  dto.street();
        String newZipCode = dto.zipCode();
        String newCity = dto.city();
        return new User(newName,
                newSurname,
                newEmail,
                newPhoneNumber,
                new Address(
                    newStreet,
                    newZipCode,
                    newCity));
    }

    public static UpdateUserResponseDto mapFromUserToUpdateUserResponseDto(User newUser) {
        UserDtoWithoutLogin userDtoWithoutLogin = new UserDtoWithoutLogin(
                newUser.getId(),
                newUser.getName(),
                newUser.getSurname(),
                newUser.getEmail(),
                newUser.getPhoneNumber(),
                newUser.getAddress().getStreet(),
                newUser.getAddress().getZipCode(),
                newUser.getAddress().getCity(),
                newUser.isActive()
        );
        return new UpdateUserResponseDto(userDtoWithoutLogin);
    }
}
