package pl.tomwodz.nursery.domain.information;

import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;
import pl.tomwodz.nursery.domain.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InformationMapper {

    public static InformationResponseDto mapFromInformationToInformationResponseDto(Information information) {
        return InformationResponseDto.builder()
                .id(information.getId())
                .author_id(information.getAuthor().getId())
                .title(information.getTitle())
                .content(information.getContent())
                .dateCreation(information.getDateCreation()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    public static Information mapFromInformationRequestDtoToInformation(InformationRequestDto requestDto) {
        return Information.builder()
                .author(new User(requestDto.author_id()))
                .title(requestDto.title())
                .content(requestDto.content())
                .dateCreation(LocalDateTime.now())
                .build();
    }

    public static Information mapFromUpdateInformationRequestDtoToInformation(Long id, InformationRequestDto requestDto) {
        return Information.builder()
            .author(new User(requestDto.author_id()))
            .title(requestDto.title())
            .content(requestDto.content())
            .dateCreation(LocalDateTime.now())
            .id(id)
            .build();
    }
}
