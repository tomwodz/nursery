package pl.tomwodz.nursery.infrastructure.presence.controller.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorPresenceResponseDto(String message,
                                       HttpStatus httpStatus,
                                       LocalDateTime ErrorDateTime) {
}
