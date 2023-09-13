package pl.tomwodz.nursery.domain.information;

import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;

import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public static List<InformationResponseDto> mapFromListInformationToListInformationResponseDto(List<Information> informations){
        return informations.stream()
                .map(information -> mapFromInformationToInformationResponseDto(information))
                .toList();
    }

}
