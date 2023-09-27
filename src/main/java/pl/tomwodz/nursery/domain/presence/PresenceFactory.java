package pl.tomwodz.nursery.domain.presence;

import pl.tomwodz.nursery.domain.child.dto.SimpleChildQueryDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class PresenceFactory {

    Presence mapFromRequestPresenceDtoToPresence(PresenceRequestDto requestDto) {
        return new Presence(
                requestDto.dataTimeEntry(),
                requestDto.dataTimeDeparture(),
                new SimpleChildQueryDto(requestDto.id()));
    }

    Presence mapFromChildIdToAutoPresence(Long childId) {
        return new Presence(LocalDateTime.of(LocalDate.now(),
                LocalTime.of(7, 00)),
                LocalDateTime.of(LocalDate.now(),
                        LocalTime.of(
                                15, 00)),
                new SimpleChildQueryDto(childId));
    }

}
