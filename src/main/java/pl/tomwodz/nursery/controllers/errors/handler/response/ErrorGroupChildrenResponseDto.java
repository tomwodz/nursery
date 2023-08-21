package pl.tomwodz.nursery.controllers.errors.handler.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorGroupChildrenResponseDto(String message,
                                            HttpStatus httpStatus,
                                           LocalDateTime ErrorDateTime) {
}