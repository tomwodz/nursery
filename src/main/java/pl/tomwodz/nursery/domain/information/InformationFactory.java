package pl.tomwodz.nursery.domain.information;

import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.user.dto.SimpleUserQueryDto;

import java.time.LocalDateTime;

class InformationFactory {
    Information mapFromInformationRequestDtoToInformation(InformationRequestDto requestDto){
        return new Information (
                LocalDateTime.now(),
                requestDto.title(),
                requestDto.content(),
                new SimpleUserQueryDto(requestDto.author_id()));
    }

}
