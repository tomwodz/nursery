package pl.tomwodz.nursery.domain.child.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DeleteChildResponseDto(String message, HttpStatus status) {
}
