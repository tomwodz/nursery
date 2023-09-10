package pl.tomwodz.nursery.domain.user.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DeleteUserResponseDto(String message, HttpStatus status) {
}
