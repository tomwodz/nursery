package pl.tomwodz.nursery.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.controllers.rest.user.UserMapper;
import pl.tomwodz.nursery.controllers.rest.user.request.CreateUserRequestDto;
import pl.tomwodz.nursery.controllers.rest.user.request.UpdateUserRequestDto;
import pl.tomwodz.nursery.controllers.rest.user.response.*;
import pl.tomwodz.nursery.exception.LoginAlreadyExistException;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.UserService;
import pl.tomwodz.nursery.validatros.UserValidator;

import java.util.List;

import static pl.tomwodz.nursery.controllers.rest.user.UserMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserRestController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<GetAllUsersResponseDto> getAllUsers(){
        List<User> allUsers = this.userService.findAll();
        GetAllUsersResponseDto response = mapFroUsersToGetAllUsersResponseDto(allUsers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetUserDto> getUserByIdWithAddressWithoutChildren(@PathVariable Long id){
        User user = this.userService.findById(id);
        GetUserDto response = mapFromUserToGetUserDto(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/roleparents")
    public ResponseEntity<GetAllUsersResponseDto> getAllUsersByRoleParents(){
        List<User> allUsers = this.userService.findByRole(User.Role.PARENT);
        GetAllUsersResponseDto response = mapFroUsersToGetAllUsersResponseDto(allUsers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/roleemployee")
    public ResponseEntity<GetAllUsersResponseDto> getAllUsersByRoleEmployee(){
        List<User> allUsers = this.userService.findByRole(User.Role.EMPLOYEE);
        GetAllUsersResponseDto response = mapFroUsersToGetAllUsersResponseDto(allUsers);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/")
    public ResponseEntity<CreateUserResponseDto> postUser(@RequestBody @Valid CreateUserRequestDto request) {
        User user = mapFromCreateUserRequestDtoToUser(request);
        UserValidator.validateUser(user);
        if(this.userService.findByLogin(user.getLogin()).isPresent()){
            throw new LoginAlreadyExistException("Login already exist.");
        };
        user.setId(0L);
        User savedUser = this.userService.save(user);
        CreateUserResponseDto response = mapFromUserToCreateUserResponseDto(savedUser);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateUserRequestDto request){
        User userFromDb = this.userService.findById(id);
        User newUser = mapFromUpdateUserRequestDtoToUser(request);
        UserValidator.validateUserToUpdate(newUser);
        this.userService.updateById(id, newUser);
        newUser.setId(userFromDb.getId());
        newUser.setRole(userFromDb.getRole()); //TODO role
        UpdateUserResponseDto response = mapFromUserToUpdateUserResponseDto(newUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteUserResponseDto> deleteUserById(@PathVariable Long id) {
        if (this.userService.findById(id).getChild().size() > 0) {
            DeleteUserResponseDto response = NotDeleteUserWithHaveChildren(id);
            return ResponseEntity.ok(response);
        }
        this.userService.deleteById(id);
        DeleteUserResponseDto response = mapFromUserToDeleteUserResponseDto(id);
        return ResponseEntity.ok(response);
    }



}
