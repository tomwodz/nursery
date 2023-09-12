package pl.tomwodz.nursery.domain.presence;

import pl.tomwodz.nursery.domain.child.dto.SimpleChildQueryDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;

class PresenceFactory {

    Presence mapFromRequestPresenceDtoToPresence(PresenceRequestDto requestDto) {
        return new Presence(
                requestDto.dataTimeEntry(),
                requestDto.dataTimeDeparture(),
                new SimpleChildQueryDto(requestDto.id()));
    }

}
