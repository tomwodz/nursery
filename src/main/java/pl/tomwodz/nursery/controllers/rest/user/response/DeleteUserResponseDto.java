package pl.tomwodz.nursery.controllers.rest.user.response;

import org.springframework.http.HttpStatus;

public record DeleteUserResponseDto(String message, HttpStatus status) {
}
