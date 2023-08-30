package pl.tomwodz.nursery.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorResponseDto(List<String> errors, HttpStatus status) {
}
