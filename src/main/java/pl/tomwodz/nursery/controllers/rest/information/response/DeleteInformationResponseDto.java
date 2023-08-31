package pl.tomwodz.nursery.controllers.rest.information.response;

import org.springframework.http.HttpStatus;

public record DeleteInformationResponseDto(String message, HttpStatus status) {
}
