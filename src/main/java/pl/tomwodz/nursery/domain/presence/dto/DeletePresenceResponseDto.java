package pl.tomwodz.nursery.domain.presence.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DeletePresenceResponseDto(String message, HttpStatus status) {
}
