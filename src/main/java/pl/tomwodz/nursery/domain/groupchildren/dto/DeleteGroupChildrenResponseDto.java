package pl.tomwodz.nursery.domain.groupchildren.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DeleteGroupChildrenResponseDto(String message, HttpStatus status) {
}
