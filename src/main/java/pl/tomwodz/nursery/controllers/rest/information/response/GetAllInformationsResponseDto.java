package pl.tomwodz.nursery.controllers.rest.information.response;

import pl.tomwodz.nursery.controllers.rest.information.response.InformationDto;

import java.util.List;

public record GetAllInformationsResponseDto(List<InformationDto> informations) {
}
