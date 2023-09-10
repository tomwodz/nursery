package pl.tomwodz.nursery.domain.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.user.dto.DeleteUserResponseDto;
import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.authentication.controller.error.LoginAlreadyExistException;
import pl.tomwodz.nursery.infrastructure.user.controller.error.UserNotFoundException;

import java.util.List;
import java.util.Optional;

import static pl.tomwodz.nursery.domain.user.UserMapper.mapFromUserRequestDtoToUser;

@AllArgsConstructor
@Transactional
public class UserFacade {

    private final UserRepository userRepository;
    private final ValidatorFacade validatorFacade;

    public UserResponseDto findUserById(Long id){
        return this.userRepository.findById(id)
                .map(UserMapper::fromUserToUserResponseDto)
                .orElseThrow(()-> new UserNotFoundException("nor found user id: " + id));
    }

    public boolean findUserByLogin(String login){
        if(this.userRepository.findByLogin(login).isPresent()){
            return true;
        } else {
            return false;
        }
    }

    public List<UserResponseDto> findAllUsers(){
        return this.userRepository.findAll()
                .stream()
                .map(UserMapper::fromUserToUserResponseDto)
                .toList();
    }

    public UserResponseDto findUserByChildId(Long id){
        return this.userRepository.findFirstByChild_Id(id)
                .map(UserMapper::fromUserToUserResponseDto)
                .orElseThrow(()-> new UserNotFoundException("nor found user id: " + id));
    }

    public List<UserResponseDto> findAllUsersByRoleParent(){
        return this.userRepository.findByRole(User.Role.PARENT)
                .stream()
                .map(UserMapper::fromUserToUserResponseDto)
                .toList();
    }

    public List<UserResponseDto> findAllUsersByRoleEmployee(){
        return this.userRepository.findByRole(User.Role.EMPLOYEE)
                .stream()
                .map(UserMapper::fromUserToUserResponseDto)
                .toList();
    }

    public UserResponseDto saveUser(UserRequestDto userRequestDto){
        validatorFacade.validationUser(userRequestDto);
        User user = mapFromUserRequestDtoToUser(userRequestDto);
        user.setRole(User.Role.PARENT);
        user.setActive(true);
        final User userSaved = this.userRepository.save(user);
        return UserMapper.fromUserToUserResponseDto(userSaved);
    }

    public UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto){
        validatorFacade.validationUserToUpdate(updateUserRequestDto);
        Optional<User> userFromDb = this.userRepository.findById(id);
        if(userFromDb.isEmpty()){
            new UserNotFoundException("nor found user id: " + id);
        }
        User user = UserMapper.mapFromUpdateUserRequestDtoToUser(id, updateUserRequestDto);
        user.setLogin(userFromDb.get().getLogin());
        user.setRole(userFromDb.get().getRole());
        user.setPassword(userFromDb.get().getPassword());
        user.setActive(userFromDb.get().isActive());
        final User userSaved = this.userRepository.save(user);
        return UserMapper.fromUserToUserResponseDto(userSaved);
    }

    public DeleteUserResponseDto deleteUser(Long id) {
        this.existsById(id);
        this.userRepository.deleteById(id);
        return DeleteUserResponseDto.builder()
                .message("You deleted user with id: " + id)
                .status(HttpStatus.OK)
                .build();
    }

    private void existsById(Long id){
        if(!this.userRepository.existsById(id)){
            throw new UserNotFoundException("not found user id: " + id);
        }
    }

}
