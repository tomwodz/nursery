package pl.tomwodz.nursery.domain.information.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DeleteInformationResponseDto(String message, HttpStatus status) {
}
