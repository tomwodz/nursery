package pl.tomwodz.nursery.infrastructure.child.controller.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorChildResponseDto(String message,
                                    HttpStatus httpStatus,
                                    LocalDateTime ErrorDateTime) {
}
