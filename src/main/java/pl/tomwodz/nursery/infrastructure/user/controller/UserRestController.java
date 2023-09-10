package pl.tomwodz.nursery.infrastructure.user.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.user.UserFacade;
import pl.tomwodz.nursery.domain.user.dto.DeleteUserResponseDto;
import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserResponseDto;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserRestController {

    private final UserFacade userFacade;
    private final ChildFacade childFacade;

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(this.userFacade.findAllUsers());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponseDto> getUserByIdWithAddressWithoutChildren(@PathVariable Long id){
        return ResponseEntity.ok(this.userFacade.findUserById(id));
    }

    @GetMapping(path = "/roleparents")
    public ResponseEntity<List<UserResponseDto>> getAllUsersByRoleParents(){
        return ResponseEntity.ok(this.userFacade.findAllUsersByRoleParent());
    }

    @GetMapping(path = "/roleemployee")
    public ResponseEntity<List<UserResponseDto>> getAllUsersByRoleEmployee(){
        return ResponseEntity.ok(this.userFacade.findAllUsersByRoleEmployee());
    }

    @PostMapping(path = "/")
    public ResponseEntity<UserResponseDto> postUser(@RequestBody @Valid UserRequestDto request) {
        return ResponseEntity.ok(this.userFacade.saveUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateUserRequestDto request){
        return ResponseEntity.ok(this.userFacade.updateUser(id, request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteUserResponseDto> deleteUserById(@PathVariable Long id) {
        if (this.childFacade.getQuantityChildrenByUserId(id) == 0) {
            return ResponseEntity.ok(this.userFacade.deleteUser(id));
        }
        return ResponseEntity.status(NOT_ACCEPTABLE).build();
    }



}
