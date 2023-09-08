package pl.tomwodz.nursery.domain.information;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationNotFoundException;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InformationFacadeTest {

    InformationFacade informationFacade = new InformationFacade(
            new InformationRepositoryTestImpl());

    InformationRequestDto informationRequestDto = InformationRequestDto.builder()
            .author_id(1L)
            .title("Test")
            .content("test")
            .build();

    @AfterEach
    void cleanUp(){
        informationFacade.findAllInformations()
                .stream()
                .forEach(i -> informationFacade.deleteInformation(i.id()));
    }

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
        assertThat(informationFacade.findAllInformations()).size().isEqualTo(0);

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
        InformationResponseDto informationResponseDto = informationFacade.saveInformation(informationRequestDto);
        InformationResponseDto informationSaved = informationFacade.findInformationById(informationResponseDto.id());
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

    @Test
    void invalidTitleInformationShouldBeThrowException(){

        //given
        InformationRequestDto informationRequestDto = InformationRequestDto.builder()
                .author_id(1L)
                .title("T")
                .content("test")
                .build();

        //then
        //when
        assertThrows(InformationValidationException.class, () -> informationFacade.saveInformation(informationRequestDto));

    }

    @Test
    void invalidContentInformationShouldBeThrowException(){

        //given
        InformationRequestDto informationRequestDto = InformationRequestDto.builder()
                .author_id(1L)
                .title("Test")
                .content("t")
                .build();

        //then
        //when
        assertThrows(InformationValidationException.class, () -> informationFacade.saveInformation(informationRequestDto));

    }




}