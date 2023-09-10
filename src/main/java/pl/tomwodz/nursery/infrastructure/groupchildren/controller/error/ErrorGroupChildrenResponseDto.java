package pl.tomwodz.nursery.infrastructure.groupchildren.controller.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorGroupChildrenResponseDto(String message,
                                            HttpStatus httpStatus,
                                           LocalDateTime ErrorDateTime) {
}
