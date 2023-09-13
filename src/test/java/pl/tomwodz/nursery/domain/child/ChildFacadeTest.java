package pl.tomwodz.nursery.domain.child;

import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.child.controller.error.ChildNotFoundException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChildFacadeTest {

    ChildRepository childRepository = new ChildRepositoryTestImpl();
    ValidatorFacade validatorFacade = mock(ValidatorFacade.class);
    ChildFacade childFacade = new ChildConfiguration().childFacade(childRepository, validatorFacade);

    ChildRequestDto childRequestDto = new ChildRequestDto("Tomasz", "Wodz", 1L,
            LocalDateTime.now(), 1L);

    @Test
    void shouldBeAbleToAddChildToRepository() {

        //given

        //when
        ChildResponseDto childResponseDto = childFacade.saveChild(childRequestDto);

        //then
        assertThat(childResponseDto.id()).isNotNull();
        assertThat(childResponseDto.name()).isEqualTo(childRequestDto.name());
        assertThat(childResponseDto.surname()).isEqualTo(childRequestDto.surname());
        assertThat(childResponseDto.groupChildren_id()).isEqualTo(childRequestDto.groupChildren_id());
        assertThat(childResponseDto.user_id()).isEqualTo(childRequestDto.user_id());
        assertThat(childResponseDto.dayBirth()).isEqualTo(childRequestDto.dayBirth());
        assertThat(childFacade.findAllChildren().size()).isEqualTo(1);

    }

    @Test
    void shouldBeAbleToRemoveFromRepository() {

        //given
        ChildResponseDto childResponseDto = childFacade.saveChild(childRequestDto);

        //when
        childFacade.deleteChildById(childResponseDto.id());

        //then
        assertThat(childFacade.findAllChildren().size()).isEqualTo(0);

    }

    @Test
    void shouldNotBeAbleToRemoveFromRepositoryWhenChildNotExist() {

        //given

        //when
        //then
        assertThrows(ChildNotFoundException.class, () -> childFacade.deleteChildById(1000L));

    }

    @Test
    void shouldBeAbleToFindChildById() {

        //given
        ChildResponseDto childResponseDto = childFacade.saveChild(childRequestDto);

        //when
        ChildResponseDto childResponseFounded = childFacade.findChildById(childResponseDto.id());

        //then
        assertThat(childResponseDto.id()).isNotNull();
        assertThat(childResponseDto.name()).isEqualTo(childRequestDto.name());
        assertThat(childResponseDto.surname()).isEqualTo(childRequestDto.surname());
        assertThat(childResponseDto.groupChildren_id()).isEqualTo(childRequestDto.groupChildren_id());
        assertThat(childResponseDto.user_id()).isEqualTo(childRequestDto.user_id());
        assertThat(childResponseDto.dayBirth()).isEqualTo(childRequestDto.dayBirth());
        assertThat(childFacade.findAllChildren().size()).isEqualTo(1);

    }

    @Test
    void shouldBeAbleToFindAllChildren() {

        //given
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);

        //when
        //then
        assertThat(childFacade.findAllChildren().size()).isEqualTo(3);

    }

    @Test
    void shouldBeAbleToFindAllChildrenByGroupChildrenId() {

        //given
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);

        //when
        //then
        assertThat(childFacade.findAllChildrenByGroupChildrenId(1L).size()).isEqualTo(3);

    }

    @Test
    void shouldBeAbleToFindAllChildrenByUserId() {

        //given
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);

        //when
        //then
        assertThat(childFacade.findChildrenByUserId(1L).size()).isEqualTo(5);

    }

    @Test
    void shouldBeAbleToGetQuantityChildrenByGroupChildrenId() {

        //given
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);

        //when
        //then
        assertThat(childFacade.getQuantityChildrenByGroupId(1L)).isEqualTo(3);

    }

    @Test
    void shouldBeAbleToGetQuantityChildrenByUserId() {

        //given
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);

        //when
        //then
        assertThat(childFacade.getQuantityChildrenByUserId(1L)).isEqualTo(2);

    }

    @Test
    void shouldNotAbleToFindChildByIdWhenIGroupChildrenNotExist() {

        //given
        //when
        //then
        assertThrows(ChildNotFoundException.class, () -> childFacade.findChildById(1000L));

    }

    @Test
    void shouldBeAbleToUpdateChildById() {

        //given
        ChildResponseDto childResponseDto = childFacade.saveChild(childRequestDto);
        ChildResponseDto childResponseDtoSaved = childFacade.findChildById(childResponseDto.id());
        ChildRequestDto childRequestDtoToUpdate = new ChildRequestDto("Update", "Update", 2L,
                LocalDateTime.now(), 2L);

        //when
        ChildResponseDto childResponseDtoUpdated =
                childFacade.updateChild(childResponseDtoSaved.id(), childRequestDtoToUpdate);

        //then
        assertThat(childResponseDtoUpdated.id()).isNotNull();
        assertThat(childResponseDtoUpdated.name()).isEqualTo(childRequestDtoToUpdate.name());
        assertThat(childResponseDtoUpdated.surname()).isEqualTo(childRequestDtoToUpdate.surname());
        assertThat(childResponseDtoUpdated.groupChildren_id()).isEqualTo(childRequestDtoToUpdate.groupChildren_id());
        assertThat(childResponseDtoUpdated.user_id()).isEqualTo(childRequestDtoToUpdate.user_id());
        assertThat(childResponseDtoUpdated.dayBirth()).isEqualTo(childRequestDtoToUpdate.dayBirth());
        assertThat(childFacade.findAllChildren().size()).isEqualTo(1);

    }

    @Test
    void shouldBeAbleCheckExistenceOfParentChildRelationship() {

        //given
        ChildResponseDto childResponseDto = childFacade.saveChild(childRequestDto);

        //when
        //then
        assertTrue(childFacade.checkExistenceOfParentChildRelationship(childResponseDto.id(),1L));
        assertFalse(childFacade.checkExistenceOfParentChildRelationship(childResponseDto.id(),3L));
    }

    @Test
    void invalidChildIdShouldGetThrowWhenCheckExistenceOfParentChildRelationship() {

        //given
        ChildResponseDto childResponseDto = childFacade.saveChild(childRequestDto);

        //when
        //then
        assertThrows(ChildNotFoundException.class, () -> childFacade.checkExistenceOfParentChildRelationship(1L,1L));

    }

    @Test
    void shouldBeAbleGetQuantityChildrenByGroups() {

        //given
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);
        childFacade.saveChild(childRequestDto);

        //when
        //then
        assertThat(childFacade.getQuantityChildrenByGroups().get(1L)).isEqualTo(5);

    }


}