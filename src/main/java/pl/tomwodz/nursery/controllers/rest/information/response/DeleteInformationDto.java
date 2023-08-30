package pl.tomwodz.nursery.controllers.rest.information.response;

import org.springframework.http.HttpStatus;

public record DeleteInformationDto(String message, HttpStatus status) {
}
