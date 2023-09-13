package pl.tomwodz.nursery.domain.user;

import org.junit.jupiter.api.Test;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;
import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationNotFoundException;
import pl.tomwodz.nursery.infrastructure.user.controller.error.UserNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserFacadeTest {

    UserRepository userRepository = new UserRepositoryTestImpl();
    ValidatorFacade validatorFacade = mock(ValidatorFacade.class);

    UserFacade userFacade = new UserConfiguration().userFacade(userRepository, validatorFacade);

    UserRequestDto userRequestDto = new UserRequestDto("tomwodz", "tomwodz", "Tomek",
            "Wodz", "twodzinski@op.pl", "600-700-800",
            "Nowa", "42-600", "Tarnowskie Góry"
    );

    @Test
    void shouldBeAbleToAddUserToRepository() {

        //given

        //when
        UserResponseDto userResponseDto = userFacade.saveUser(userRequestDto);

        //then
        assertThat(userResponseDto.id()).isNotNull();
        assertThat(userResponseDto.login()).isEqualTo(userRequestDto.login());
        assertThat(userResponseDto.name()).isEqualTo(userRequestDto.name());
        assertThat(userResponseDto.surname()).isEqualTo(userRequestDto.surname());
        assertThat(userResponseDto.email()).isEqualTo(userRequestDto.email());
        assertThat(userResponseDto.phoneNumber()).isEqualTo(userRequestDto.phoneNumber());
        assertThat(userResponseDto.street()).isEqualTo(userRequestDto.street());
        assertThat(userResponseDto.zipCode()).isEqualTo(userRequestDto.zipCode());
    }

    @Test
    void shouldBeAbleToAddInformationToRepository() {

        //given

        //when
        UserResponseDto userResponseDto = this.userFacade.saveUser(userRequestDto);

        //then
        assertThat(userResponseDto.id()).isNotNull();
        assertThat(userResponseDto.login()).isEqualTo(userRequestDto.login());
        assertThat(userResponseDto.name()).isEqualTo(userRequestDto.name());
        assertThat(userResponseDto.surname()).isEqualTo(userRequestDto.surname());
        assertThat(userResponseDto.email()).isEqualTo(userRequestDto.email());
        assertThat(userResponseDto.phoneNumber()).isEqualTo(userRequestDto.phoneNumber());
        assertThat(userResponseDto.street()).isEqualTo(userRequestDto.street());
        assertThat(userResponseDto.zipCode()).isEqualTo(userRequestDto.zipCode());

    }

    @Test
    void shouldBeAbleToRemoveFromRepository() {

        //given
        UserResponseDto userResponseDto = this.userFacade.saveUser(userRequestDto);

        //when
        userFacade.deleteUser(userResponseDto.id());

        //then
        assertThat(userFacade.findAllUsers().size()).isEqualTo(0);

    }

    @Test
    void shouldNotBeAbleToRemoveFromRepositoryWhenUserNotExist() {

        //given
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> userFacade.deleteUser(1000L));

    }

    @Test
    void shouldBeAbleToFindInformationById() {

        //given
        UserResponseDto userResponseDto = this.userFacade.saveUser(userRequestDto);

        //when
        UserResponseDto userResponseDtoFounded = userFacade.findUserById(userResponseDto.id());

        //then
        assertThat(userResponseDtoFounded.id()).isNotNull();
        assertThat(userResponseDtoFounded.login()).isEqualTo(userRequestDto.login());
        assertThat(userResponseDtoFounded.name()).isEqualTo(userRequestDto.name());
        assertThat(userResponseDtoFounded.surname()).isEqualTo(userRequestDto.surname());
        assertThat(userResponseDtoFounded.email()).isEqualTo(userRequestDto.email());
        assertThat(userResponseDtoFounded.phoneNumber()).isEqualTo(userRequestDto.phoneNumber());
        assertThat(userResponseDtoFounded.street()).isEqualTo(userRequestDto.street());
        assertThat(userResponseDtoFounded.zipCode()).isEqualTo(userRequestDto.zipCode());

    }

    @Test
    void shouldBeAbleToFindAllUsers() {

        //given
        userFacade.saveUser(userRequestDto);
        userFacade.saveUser(userRequestDto);
        userFacade.saveUser(userRequestDto);
        userFacade.saveUser(userRequestDto);

        //when
        //then
        assertThat(userFacade.findAllUsers().size()).isEqualTo(4);

    }

    @Test
    void shouldNotAbleToFindUserByIdWhenInformationNotExist() {

        //given
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> userFacade.findUserById(1000L));

    }

    @Test
    void shouldBeAbleToUpdateUserById() {

        //given
        UserResponseDto userResponseDtoSaved = userFacade.saveUser(userRequestDto);
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder()
                .name("Update")
                .surname("Update")
                .city("Update")
                .phoneNumber("999-999-999")
                .street("Update")
                .zipCode("99-999")
                .city("Update")
                .build();

        //when
        UserResponseDto userResponseDtoUpdated = userFacade.updateUser(userResponseDtoSaved.id(),
                updateUserRequestDto);

        //then
        assertThat(userResponseDtoUpdated.id()).isEqualTo(userResponseDtoSaved.id());
        assertThat(userResponseDtoUpdated.name()).isEqualTo(updateUserRequestDto.name());
        assertThat(userResponseDtoUpdated.surname()).isEqualTo(updateUserRequestDto.surname());
        assertThat(userResponseDtoUpdated.email()).isEqualTo(updateUserRequestDto.email());
        assertThat(userResponseDtoUpdated.phoneNumber()).isEqualTo(updateUserRequestDto.phoneNumber());
        assertThat(userResponseDtoUpdated.street()).isEqualTo(updateUserRequestDto.street());
        assertThat(userResponseDtoUpdated.zipCode()).isEqualTo(updateUserRequestDto.zipCode());

    }

    @Test
    void shouldBeNotAbleToUpdateUserByIdNotExist() {

        //given
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder()
                .name("Update")
                .surname("Update")
                .city("Update")
                .phoneNumber("999-999-999")
                .street("Update")
                .zipCode("99-999")
                .city("Update")
                .build();
        //when

        //then
        assertThrows(UserNotFoundException.class, () -> userFacade.updateUser(100L, updateUserRequestDto));

    }

    @Test
    void shouldBeAbleToFindUserByName() {

        //given
        UserResponseDto userResponseDtoSaved = userFacade.saveUser(userRequestDto);

        //when
        //then
        assertTrue(userFacade.existsUserByLogin("tomwodz"));

    }

    @Test
    void shouldBeAbleToFindUsersByRoleParent() {

        //given
        userFacade.saveUser(userRequestDto);
        userFacade.saveUser(userRequestDto);
        userFacade.saveUser(userRequestDto);

        //when
        //then
        assertThat(userFacade.findAllUsersByRoleParent().size()).isEqualTo(3);

    }

    @Test
    void shouldBeAbleToFindUsersByRoleEmployee() {

        //given
        userFacade.saveUser(userRequestDto);
        userFacade.saveUser(userRequestDto);
        UserResponseDto userResponseDtoSaved = userFacade.saveUser(userRequestDto);
        UpdateUserRequestDto updateUserRequestDto = UpdateUserRequestDto.builder()
                .name("Update")
                .surname("Update")
                .city("Update")
                .phoneNumber("999-999-999")
                .street("Update")
                .zipCode("99-999")
                .city("Update")
                .role("EMPLOYEE")
                .build();
        UserResponseDto userResponseDtoUpdated = userFacade.updateUser(userResponseDtoSaved.id(),
                updateUserRequestDto);

        //when
        //then
        assertThat(userFacade.findAllUsersByRoleEmployee().size()).isEqualTo(1);

    }

    @Test
    void shouldBeAbleToChangeActiveUserById(){

        //given
        UserResponseDto userResponseDtoSaved = userFacade.saveUser(userRequestDto);

        //when
        //then
        assertFalse(userFacade.changeActiveUserById(userResponseDtoSaved.id()));
    }

}