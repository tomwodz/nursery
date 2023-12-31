package pl.tomwodz.nursery.domain.information;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class InformationFacadeTest {
    InformationRepository informationRepository = new InformationRepositoryTestImpl();

    ValidatorFacade validatorFacade = mock(ValidatorFacade.class);


    InformationFacade informationFacade = new InformationConfiguration().
            informationFacade(informationRepository, validatorFacade
                    );

    InformationRequestDto informationRequestDto = InformationRequestDto.builder()
            .author_id(1L)
            .title("Test")
            .content("test")
            .build();

    @Test
    void shouldBeAbleToAddInformationToRepository() {

        //given

        //when
        InformationResponseDto informationResponseDto = informationFacade.saveInformation(informationRequestDto);

        //then
        assertThat(informationResponseDto.id()).isNotNull();
        assertThat(informationResponseDto.author_id()).isEqualTo(informationRequestDto.author_id());
        assertThat(informationResponseDto.title()).isEqualTo(informationRequestDto.title());
        assertThat(informationResponseDto.content()).isEqualTo(informationRequestDto.content());

    }

    @Test
    void shouldBeAbleToRemoveFromRepository() {

        //given
        InformationResponseDto informationResponseDto = informationFacade.saveInformation(informationRequestDto);

        //when
        informationFacade.deleteInformation(informationResponseDto.id());

        //then
        assertThat(informationFacade.findAllInformations().size()).isEqualTo(0);

    }

    @Test
    void shouldNotBeAbleToRemoveFromRepositoryWhenInformationNotExist() {

        //given
        //when
        //then
        assertThrows(InformationNotFoundException.class, () -> informationFacade.deleteInformation(1000L));

    }

    @Test
    void shouldBeAbleToFindInformationById() {

        //given
        InformationResponseDto informationResponseDto = informationFacade.saveInformation(informationRequestDto);

        //when
        InformationResponseDto informationFounded = informationFacade.findInformationById(informationResponseDto.id());

        //then
        assertThat(informationFounded.id()).isNotNull();
        assertThat(informationFounded.author_id()).isEqualTo(informationRequestDto.author_id());
        assertThat(informationFounded.title()).isEqualTo(informationRequestDto.title());
        assertThat(informationFounded.content()).isEqualTo(informationRequestDto.content());

    }

    @Test
    void shouldBeAbleToFindAllInformations() {

        //given
        informationFacade.saveInformation(informationRequestDto);
        informationFacade.saveInformation(informationRequestDto);
        informationFacade.saveInformation(informationRequestDto);

        //when
        //then
        assertThat(informationFacade.findAllInformations().size()).isEqualTo(3);

    }

    @Test
    void shouldNotAbleToFindInformationByIdWhenInformationNotExist() {

        //given
        //when
        //then
        assertThrows(InformationNotFoundException.class, () -> informationFacade.findInformationById(1000L));

    }

    @Test
    void shouldBeAbleToUpdateInformationById() {

        //given
        InformationResponseDto informationSaved = informationFacade.saveInformation(informationRequestDto);
        informationFacade.findInformationById(informationSaved.id());
        InformationRequestDto informationToUpdate = InformationRequestDto.builder()
                .author_id(1L)
                .title("Update")
                .content("Update")
                .build();

        //when
        InformationResponseDto informationUpdated = informationFacade
                .updateInformation(
                        informationSaved.id(),
                        informationToUpdate);

        //then
        assertThat(informationUpdated.id()).isEqualTo(informationSaved.id());
        assertThat(informationUpdated.author_id()).isEqualTo(informationToUpdate.author_id());
        assertThat(informationUpdated.title()).isEqualTo(informationToUpdate.title());
        assertThat(informationUpdated.author_id()).isEqualTo(informationToUpdate.author_id());

    }

}