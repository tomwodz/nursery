package pl.tomwodz.nursery.domain.groupchildren;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.exception.validation.GroupChildrenValidationException;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GroupChildrenFacadeTest {

    GroupChildrenFacade groupChildrenFacade = new GroupChildrenFacade(
            new GroupChildrenRepositoryTestImpl()
    );

    GroupChildrenRequestDto groupChildrenRequestDto = new GroupChildrenRequestDto("Test");

    @AfterEach
    void cleanUp(){
        groupChildrenFacade.findAllGroupsChildren()
                .stream()
                .forEach(g -> groupChildrenFacade.deleteGroupChildren(g.id()));
    }
    @Test
    void shouldBeAbleToAddGroupChildrenToRepository() {

        //given

        //when
        GroupChildrenResponseDto groupChildrenResponseDto =
                groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);

        //then
        assertThat(groupChildrenResponseDto.id()).isNotNull();
        assertThat(groupChildrenResponseDto.name()).isEqualTo(groupChildrenRequestDto.name());

    }

    @Test
    void shouldBeAbleToRemoveFromRepository() {

        //given
        GroupChildrenResponseDto groupChildrenResponseDto =
                groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);

        //when
        groupChildrenFacade.deleteGroupChildren(groupChildrenResponseDto.id());

        //then
        assertThat(groupChildrenFacade.findAllGroupsChildren()).size().isEqualTo(0);

    }

    @Test
    void shouldBeMessageBeforeRemoveFromRepository() {

        //given
        GroupChildrenResponseDto groupChildrenResponseDto =
                groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);

        //when
        groupChildrenFacade.deleteGroupChildren(groupChildrenResponseDto.id());

        //then
        assertThat(groupChildrenFacade.findAllGroupsChildren()).size().isEqualTo(0);

    }

    @Test
    void shouldNotBeAbleToRemoveFromRepositoryWhenGroupChildrenNotExist() {

        //given
        //when
        //then
        assertThrows(GroupChildrenNotFoundException.class, () -> groupChildrenFacade.deleteGroupChildren(1000L));

    }

    @Test
    void shouldBeAbleToFindGroupChildrenById() {

        //given
        GroupChildrenResponseDto groupChildrenResponseDto =
                groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);

        //when
        GroupChildrenResponseDto groupChildrenFounded =
                groupChildrenFacade.findGroupChildrenById(groupChildrenResponseDto.id());

        //then
        assertThat(groupChildrenFounded.id()).isNotNull();
        assertThat(groupChildrenFounded.name()).isEqualTo(groupChildrenRequestDto.name());

    }

    @Test
    void shouldBeAbleToFindAllGroupsChildren() {

        //given
        groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);
        groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);
        groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);
        groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);

        //when
        //then
        assertThat(groupChildrenFacade.findAllGroupsChildren().size()).isEqualTo(4);

    }

    @Test
    void shouldNotAbleToFindGroupChildrenByIdWhenIGroupChildrenNotExist() {

        //given
        //when
        //then
        assertThrows(GroupChildrenNotFoundException.class, () -> groupChildrenFacade.findGroupChildrenById(1000L));

    }

    @Test
    void shouldBeAbleToUpdateGroupChildrenById() {

        //given
        GroupChildrenResponseDto groupChildrenSaved =
                groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto);
        groupChildrenFacade.findGroupChildrenById(groupChildrenSaved.id());
        GroupChildrenRequestDto groupChildrenToUpdate = new GroupChildrenRequestDto("Update");

        //when
        GroupChildrenResponseDto groupChildrenUpdated =
                groupChildrenFacade.updateGroupChildren(groupChildrenSaved.id(), groupChildrenToUpdate);

        //then
        assertThat(groupChildrenUpdated.id()).isEqualTo(groupChildrenSaved.id());
        assertThat(groupChildrenUpdated.name()).isEqualTo(groupChildrenToUpdate.name());

    }

    @Test
    void invalidNameGroupChildrenShouldBeThrowException(){

        //given
        GroupChildrenRequestDto groupChildrenRequestDto = new GroupChildrenRequestDto("t");

        //then
        //when
        assertThrows(GroupChildrenValidationException.class, () -> groupChildrenFacade.saveGroupChildren(groupChildrenRequestDto));

    }
}