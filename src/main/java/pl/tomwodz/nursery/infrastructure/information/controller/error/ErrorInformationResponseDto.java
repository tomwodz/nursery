package pl.tomwodz.nursery.infrastructure.information.controller.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorInformationResponseDto(String message,
                                          HttpStatus httpStatus,
                                          LocalDateTime ErrorDateTime) {
}
