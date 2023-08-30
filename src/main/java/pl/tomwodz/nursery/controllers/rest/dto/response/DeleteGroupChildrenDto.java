package pl.tomwodz.nursery.controllers.rest.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteGroupChildrenDto(String message, HttpStatus status) {
}
