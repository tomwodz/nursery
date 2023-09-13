package pl.tomwodz.nursery.domain.groupchildren;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotDeleteException;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GroupChildrenFacadeTest {

    GroupChildrenRepository groupChildrenRepository = new GroupChildrenRepositoryTestImpl();


    ValidatorFacade validatorFacade = mock(ValidatorFacade.class);
   ChildFacade childFacade = mock(ChildFacade.class);

    GroupChildrenFacade groupChildrenFacade = new GroupChildrenConfiguration()
            .groupChildrenFacade(groupChildrenRepository, validatorFacade, childFacade);

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

}