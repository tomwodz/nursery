package pl.tomwodz.nursery.domain.presence;

import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;
import pl.tomwodz.nursery.infrastructure.presence.controller.error.PresenceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class PresenceFacadeTest {

    PresenceRepository presenceRepository = new PresenceRepositoryTestImpl();

    ChildFacade childFacade = mock(ChildFacade.class);
    PresenceFacade presenceFacade = new PresenceConfiguration().presenceFacade(presenceRepository, childFacade);

    PresenceRequestDto presenceRequestDto = PresenceRequestDto.builder()
            .dataTimeEntry(LocalDateTime.now())
            .dataTimeDeparture(LocalDateTime.now())
            .id(1L) //Child
            .build();

    @Test
    void shouldBeAbleToAddPresenceToRepository() {

        //given

        //when
        PresenceResponseDto presenceResponseDto = presenceFacade.savePresence(presenceRequestDto);

        //then
        assertThat(presenceResponseDto.id()).isNotNull();
        assertThat(presenceResponseDto.child_id()).isEqualTo(presenceRequestDto.id());
        assertThat(presenceResponseDto.dataTimeEntry()).isEqualTo(presenceRequestDto.dataTimeEntry());
        assertThat(presenceResponseDto.dataTimeDeparture()).isEqualTo(presenceRequestDto.dataTimeDeparture());

    }

    @Test
    void shouldBeAbleToRemoveFromRepository() {

        //given
        PresenceResponseDto presenceResponseDto = presenceFacade.savePresence(presenceRequestDto);

        //when
        presenceFacade.deletePresence(presenceResponseDto.id());

        //then
        assertThat(presenceFacade.findAllPresences().size()).isEqualTo(0);

    }

    @Test
    void shouldNotBeAbleToRemoveFromRepositoryWhenPresenceNotExist() {

        //given
        //when
        //then
        assertThrows(PresenceNotFoundException.class, () -> presenceFacade.deletePresence(1000L));

    }

    @Test
    void shouldBeAbleToFindPresenceById() {

        //given
        PresenceResponseDto presenceResponseDtoSaved = presenceFacade.savePresence(presenceRequestDto);

        //when
        PresenceResponseDto presenceResponseDtoFounded = presenceFacade.findPresenceById(presenceResponseDtoSaved.id());

        //then
        assertThat(presenceResponseDtoFounded.id()).isNotNull();
        assertThat(presenceResponseDtoFounded .child_id()).isEqualTo(presenceRequestDto.id());
        assertThat(presenceResponseDtoFounded .dataTimeEntry()).isEqualTo(presenceRequestDto.dataTimeEntry());
        assertThat(presenceResponseDtoFounded .dataTimeDeparture()).isEqualTo(presenceRequestDto.dataTimeDeparture());

    }

    @Test
    void shouldBeAbleToFindAllPresence() {

        //given
        presenceFacade.savePresence(presenceRequestDto);
        presenceFacade.savePresence(presenceRequestDto);

        //when
        //then
        assertThat(presenceFacade.findAllPresences().size()).isEqualTo(2);

    }

    @Test
    void shouldNotAbleToFindPresenceByIdWhenPresenceNotExist() {

        //given
        //when
        //then
        assertThrows(PresenceNotFoundException.class, () -> presenceFacade.findPresenceById(1000L));

    }

    @Test
    void shouldBeAbleToUpdatePresenceById() {

        //given
        PresenceResponseDto presenceResponseDtoSaved = presenceFacade.savePresence(presenceRequestDto);
        PresenceRequestDto presenceRequestDtoToUpdate = PresenceRequestDto.builder()
                .dataTimeEntry(LocalDateTime.now())
                .dataTimeDeparture(LocalDateTime.now())
                .id(2L)
                .build();

        //when
        PresenceResponseDto presenceResponseDtoUpdated = presenceFacade.updatePresence(presenceResponseDtoSaved.id(), presenceRequestDtoToUpdate);

        //then
        assertThat(presenceResponseDtoUpdated.id()).isEqualTo(presenceResponseDtoSaved.id());
        assertThat(presenceResponseDtoUpdated.child_id()).isEqualTo(presenceRequestDtoToUpdate.id());
        assertThat(presenceResponseDtoUpdated.dataTimeEntry()).isEqualTo(presenceRequestDtoToUpdate.dataTimeEntry());
        assertThat(presenceResponseDtoUpdated.dataTimeDeparture()).isEqualTo(presenceRequestDtoToUpdate.dataTimeDeparture());

    }

    @Test
    void shouldAbleToFindPresenceByBetweenDates() {

        //given
        PresenceRequestDto presenceRequestDtoNew = PresenceRequestDto.builder()
                .dataTimeEntry(LocalDateTime.of(2023,9,13,07,00))
                .dataTimeDeparture(LocalDateTime.of(2023,9,13,15,00))
                .id(1L) //Child
                .build();
        PresenceResponseDto presenceResponseDtoSaved = presenceFacade.savePresence(presenceRequestDtoNew);

        presenceFacade.savePresence(PresenceRequestDto.builder()
                .dataTimeEntry(LocalDateTime.of(2023,9,15,07,00))
                .dataTimeDeparture(LocalDateTime.of(2023,9,15,15,00))
                .id(1L) //Child
                .build());


        //when
        List<PresenceResponseDto> list = presenceFacade
                .findAllPresencesByChildIdBetweenDates(
                1L,
                LocalDateTime.of(2023,9,13,06,59),
                LocalDateTime.of(2023,9,14,15,01)
        );

        //then
        assertThat(list.size()).isEqualTo(1);

    }

}