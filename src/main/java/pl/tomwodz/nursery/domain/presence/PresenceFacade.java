package pl.tomwodz.nursery.domain.presence;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.presence.dto.DeletePresenceResponseDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationNotFoundException;
import pl.tomwodz.nursery.infrastructure.presence.controller.error.PresenceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Transactional
public class PresenceFacade {

    private final PresenceRepository presenceRepository;
    private final PresenceFactory presenceFactory;

    public PresenceResponseDto findPresenceById(Long id) {
        return this.presenceRepository.findById(id)
                .map(PresenceMapper::mapFromPresenceToPresenceResponseDto)
                .orElseThrow(() -> new PresenceNotFoundException("not found presence id: " + id));
    }

    public List<PresenceResponseDto> findAllPresences() {
        return this.presenceRepository.findAll(Sort.by(Sort.Direction.DESC, "day"))
                .stream()
                .map(PresenceMapper::mapFromPresenceToPresenceResponseDto)
                .toList();
    }

    public List<PresenceResponseDto> findAllPresencesByGroupChildrenId(Long id) {
        return this.presenceRepository.findAllByChild_GroupChildren_IdOrderByDataTimeEntryDesc(id)
                .stream()
                .map(PresenceMapper::mapFromPresenceToPresenceResponseDto)
                .toList();
    }

    public List<PresenceResponseDto> findAllPresencesByGroupChildrenIdBetweenDates(Long id,
                                                                                   LocalDateTime dataTimeEntry,
                                                                                   LocalDateTime dataTimeDeparture) {
        return this.presenceRepository
                .findAllByChild_GroupChildren_IdAndDataTimeEntryAfterAndDataTimeDepartureBefore(
                        id, dataTimeEntry, dataTimeDeparture)
                .stream()
                .map(PresenceMapper::mapFromPresenceToPresenceResponseDto)
                .toList();
    }

    public List<PresenceResponseDto> findAllPresencesByChildIdBetweenDates(Long id,
                                                                                   LocalDateTime dataTimeEntry,
                                                                                   LocalDateTime dataTimeDeparture) {
        return this.presenceRepository
                .findAllByChild_IdAndDataTimeEntryAfterAndDataTimeDepartureBefore(
                        id,dataTimeEntry, dataTimeDeparture)
                .stream()
                .map(PresenceMapper::mapFromPresenceToPresenceResponseDto)
                .toList();
    }

    public List<PresenceResponseDto> findAllPresencesByChildId(Long id) {
        return this.presenceRepository.findAllByChild_Id(id)
                .stream()
                .map(PresenceMapper::mapFromPresenceToPresenceResponseDto)
                .toList();
    }

    public PresenceResponseDto savePresence(PresenceRequestDto requestPresenceDto) {
        Presence presence = presenceFactory.mapFromRequestPresenceDtoToPresence(requestPresenceDto);
        Presence presenceSaved = this.presenceRepository.save(presence);
        return PresenceMapper.mapFromPresenceToPresenceResponseDto(presenceSaved);
    }

    public PresenceResponseDto updatePresence(Long id, PresenceRequestDto requestPresenceDto) {
        Presence presence = presenceFactory.mapFromRequestPresenceDtoToPresence(requestPresenceDto);
        presence.setId(id);
        Presence presenceSaved = this.presenceRepository.save(presence);
        return PresenceMapper.mapFromPresenceToPresenceResponseDto(presenceSaved);
    }

    public DeletePresenceResponseDto deletePresence(Long id) {
        this.existsById(id);
        this.presenceRepository.deleteById(id);
        return DeletePresenceResponseDto.builder()
                .message("You deleted information with id: " + id)
                .status(HttpStatus.OK)
                .build();
    }

    private void existsById(Long id){
        if(!this.presenceRepository.existsById(id)){
            throw new PresenceNotFoundException("not found presence id: " + id);
        }
    }
}
