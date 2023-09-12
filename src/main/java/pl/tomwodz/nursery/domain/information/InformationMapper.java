package pl.tomwodz.nursery.domain.information;

import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;

import java.time.format.DateTimeFormatter;

class InformationMapper {

    public static InformationResponseDto mapFromInformationToInformationResponseDto(Information information) {
        return InformationResponseDto.builder()
                .id(information.getId())
                .author_id(information.getAuthor().getId())
                .title(information.getTitle())
                .content(information.getContent())
                .dateCreation(information.getDateCreation())
                .build();
    }

}
