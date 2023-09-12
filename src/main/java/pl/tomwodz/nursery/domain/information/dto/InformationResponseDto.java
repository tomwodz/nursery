package pl.tomwodz.nursery.domain.information.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InformationResponseDto(Long id,
                                     Long author_id,
                                     String title,
                                     String content,
                                     LocalDateTime dateCreation) {
}
