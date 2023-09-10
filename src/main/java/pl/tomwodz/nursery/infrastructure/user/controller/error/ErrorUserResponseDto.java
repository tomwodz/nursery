package pl.tomwodz.nursery.infrastructure.user.controller.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorUserResponseDto(String message,
                                   HttpStatus httpStatus,
                                   LocalDateTime ErrorDateTime) {
}
