package pl.tomwodz.nursery.controllers.rest.information;

import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.controllers.rest.information.request.CreateInformationRequestDto;
import pl.tomwodz.nursery.controllers.rest.information.request.UpdateInformationRequestDto;
import pl.tomwodz.nursery.controllers.rest.information.response.*;
import pl.tomwodz.nursery.model.Information;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InformationMapper {

    public static InformationDto mapFromInformationToInformationDto(Information information) {
        return new InformationDto(information.getId(),
                information.getAuthor().getId(),
                information.getTitle(),
                information.getContent(),
                information.getDateCreation()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                );
    }
    public static GetAllInformationsResponseDto mapFromInformationsToGetAllInformationsResponseDto(List<Information> allInformations) {
        List<InformationDto> informationDtos = allInformations.stream()
                .map(InformationMapper::mapFromInformationToInformationDto)
                .toList();
        return new GetAllInformationsResponseDto(informationDtos);
    }

    public static GetInformationDto mapFromInformationToGetInformationDto(Information information) {
        InformationDto informationDto = mapFromInformationToInformationDto(information);
        return new GetInformationDto(informationDto);
    }

    public static DeleteInformationResponseDto mapFromInformationToDeleteInformationResponseDto(Long id) {
        return new DeleteInformationResponseDto("You deleted information with id: " + id, HttpStatus.OK);
    }

    public static Information mapFromCreateInformationRequestDtoToInformation(CreateInformationRequestDto dto) {
        Information information = new Information();
        information.setId(0L);
        information.setDateCreation(LocalDateTime.now());
        information.setTitle(dto.title());
        information.setContent(dto.content());
        return information;
    }

    public static CreateInformationResponseDto mapFromInformationToCreateInformationResponseDto(Information savedInformation) {
        InformationDto informationDto = mapFromInformationToInformationDto(savedInformation);
        return new CreateInformationResponseDto(informationDto);
    }

    public static Information mapFromUpdateInformationRequestDtoToInformation(UpdateInformationRequestDto dto) {
        String newTitle = dto.title();
        String newContent = dto.content();
        return new Information(newTitle, newContent);
    }

    public static UpdateInformationResponseDto mapFromInformationToUpdateInformationResponseDto(Information newInformation) {
        return new UpdateInformationResponseDto(
                newInformation.getId(),
                newInformation.getAuthor().getId(),
                newInformation.getTitle(),
                newInformation.getContent(),
                newInformation.getDateCreation()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
