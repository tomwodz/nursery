package pl.tomwodz.nursery.domain.presence;

import pl.tomwodz.nursery.domain.child.Child;
import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;

public class PresenceMapper {
    public static PresenceResponseDto mapFromPresenceToPresenceResponseDto(Presence presence) {
        return PresenceResponseDto.builder()
                .id(presence.getId())
                .child_id(presence.child.getId())
                .dataTimeEntry(presence.getDataTimeEntry())
                .dataTimeDeparture(presence.getDataTimeDeparture())
                .build();
    }

    public static Presence mapFromRequestPresenceDtoToPresence(PresenceRequestDto requestDto) {
        return Presence.builder()
                .child(new Child(requestDto.child_id()))
                .dataTimeEntry(requestDto.dataTimeEntry())
                .dataTimeDeparture(requestDto.dataTimeDeparture())
                .build();
    }
}
