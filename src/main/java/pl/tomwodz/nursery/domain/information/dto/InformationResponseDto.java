package pl.tomwodz.nursery.domain.information.dto;

import lombok.Builder;

@Builder
public record InformationResponseDto(Long id,
                                     Long author_id,
                                     String title,
                                     String content,
                                     String dateCreation) {
}
