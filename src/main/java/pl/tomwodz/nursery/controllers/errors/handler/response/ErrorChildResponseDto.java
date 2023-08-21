package pl.tomwodz.nursery.controllers.errors.handler.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorChildResponseDto(String message,
                                    HttpStatus httpStatus,
                                    LocalDateTime ErrorDateTime) {
}
