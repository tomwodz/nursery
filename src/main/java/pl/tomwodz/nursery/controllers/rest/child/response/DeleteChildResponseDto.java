package pl.tomwodz.nursery.controllers.rest.child.response;

import org.springframework.http.HttpStatus;

public record DeleteChildResponseDto(String message, HttpStatus status) {
}
