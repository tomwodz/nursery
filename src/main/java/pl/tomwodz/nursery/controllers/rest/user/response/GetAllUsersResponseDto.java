package pl.tomwodz.nursery.controllers.rest.user.response;

import pl.tomwodz.nursery.controllers.rest.user.response.UserDto;

import java.util.List;

public record GetAllUsersResponseDto(List<UserDto> users) {
}
