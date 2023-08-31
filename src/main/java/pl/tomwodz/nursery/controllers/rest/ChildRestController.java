package pl.tomwodz.nursery.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.controllers.rest.child.ChildMapper;
import pl.tomwodz.nursery.controllers.rest.child.request.CreateChildRequestDto;
import pl.tomwodz.nursery.controllers.rest.child.request.UpdateChildRequestDto;
import pl.tomwodz.nursery.controllers.rest.child.response.*;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.ChildService;
import pl.tomwodz.nursery.services.GroupChildrenService;
import pl.tomwodz.nursery.services.UserService;
import pl.tomwodz.nursery.validatros.ChildValidator;

import java.util.List;

import static pl.tomwodz.nursery.controllers.rest.child.ChildMapper.*;
import static pl.tomwodz.nursery.validatros.ChildValidator.validateDayBirth;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/child")
public class ChildRestController {

    private final ChildService childService;
    private final UserService userService;
    private final GroupChildrenService groupChildrenService;
    @GetMapping
    public ResponseEntity<GetAllChildrenResponseDto> getAllChildren() {
        List<Child> allChildren = this.childService.findAll();
        GetAllChildrenResponseDto response = mapFromChildToGetAllChildrenResponstDto(allChildren);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetChildDto> getChildById(@PathVariable Long id){
        Child child = this.childService.findById(id);
        GetChildDto response = mapFromChildToGetChildDto(child);
        return ResponseEntity.ok(response);
    }
    @PostMapping(path = "/user/{id}")
    public ResponseEntity<CreateChildResponseDto> postChild(
            @RequestBody @Valid CreateChildRequestDto request,
            @PathVariable Long id) {
        User userFromDb = this.userService.findById(id);
        validateDayBirth(request.dayBirth());
        Child child = mapFromCreateChildRequestDtoToChild(request);
        child.setParent(userFromDb);
        child.setGroupChildren(this.groupChildrenService.getGroupChildrenByNewChild());
        Child savedChild = this.childService.save(child);
        CreateChildResponseDto response = mapFromChildToCreateChildResponseDto(savedChild);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/user/{user_id}/groupchildren/{groupChildren_id}")
    public ResponseEntity<UpdateChildResponseDto> update(@PathVariable Long id,
                                                         @PathVariable Long user_id,
                                                         @PathVariable Long groupChildren_id,
                                                         @RequestBody @Valid UpdateChildRequestDto request){
        ChildValidator.validateDayBirth(request.dayBirth());
        Child childFromDb = this.childService.findById(id);
        User userFromDb = this.userService.findById(user_id);
        GroupChildren groupChildren = this.groupChildrenService.findById(groupChildren_id);
        Child newChild = ChildMapper.mapFromUpdateChildRequestDtoToChild(request);
        newChild.setParent(userFromDb);
        newChild.setGroupChildren(groupChildren);
        this.childService.updateById(id, newChild);
        newChild.setId(childFromDb.getId());
        UpdateChildResponseDto response = ChildMapper.mapFromChildToUpdateChildResponseDto(newChild);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteChildResponseDto> deleteChildById(@PathVariable Long id) {
        this.childService.deleteById(id);
        DeleteChildResponseDto response = mapFromChildToDeleteChildResponseDto(id);
        return ResponseEntity.ok(response);
    }

}
