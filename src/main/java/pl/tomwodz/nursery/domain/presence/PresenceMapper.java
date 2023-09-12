package pl.tomwodz.nursery.domain.presence;

import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;

class PresenceMapper {
    public static PresenceResponseDto mapFromPresenceToPresenceResponseDto(Presence presence) {
        return PresenceResponseDto.builder()
                .id(presence.getId())
                .child_id(presence.child.getId())
                .dataTimeEntry(presence.getDataTimeEntry())
                .dataTimeDeparture(presence.getDataTimeDeparture())
                .build();
    }


}
