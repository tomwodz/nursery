package pl.tomwodz.nursery.exception.handler.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorPresenceResponseDto(String message,
                                       HttpStatus httpStatus,
                                       LocalDateTime ErrorDateTime) {
}
