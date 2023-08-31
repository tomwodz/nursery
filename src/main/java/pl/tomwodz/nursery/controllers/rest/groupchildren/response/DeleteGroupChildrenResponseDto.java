package pl.tomwodz.nursery.controllers.rest.groupchildren.response;

import org.springframework.http.HttpStatus;

public record DeleteGroupChildrenResponseDto(String message, HttpStatus status) {
}
