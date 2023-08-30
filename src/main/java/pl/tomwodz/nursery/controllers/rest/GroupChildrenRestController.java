package pl.tomwodz.nursery.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.controllers.rest.dto.request.CreateGroupChildrenRequestDto;
import pl.tomwodz.nursery.controllers.rest.dto.request.UpdateGroupChildrenRequestDto;
import pl.tomwodz.nursery.controllers.rest.dto.response.*;
import pl.tomwodz.nursery.controllers.rest.mapper.GroupChildrenMapper;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.services.GroupChildrenService;

import java.util.List;

import static pl.tomwodz.nursery.controllers.rest.mapper.GroupChildrenMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/groupchildren")
public class GroupChildrenRestController {
    private final GroupChildrenService groupChildrenService;

    @GetMapping
    public ResponseEntity<GetAllGroupsChildrenResponseDto> getAllGroupsChildren() {
        List<GroupChildren> allGroupsChildren = this.groupChildrenService.findAll();
        GetAllGroupsChildrenResponseDto response = mapFromGroupChildrenToGetAllGroupChildrenResponseDto(allGroupsChildren);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetGroupChildrenDto> getGroupChildrenById(@PathVariable Long id){
        GroupChildren groupChildren = this.groupChildrenService.findById(id);
        GetGroupChildrenDto response = mapFromGroupChildrenToGetGroupChildrenDto(groupChildren);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateGroupChildrenResponseDto> postGroupChildren(
            @RequestBody @Valid CreateGroupChildrenRequestDto request) {
        GroupChildren groupChildren = GroupChildrenMapper.CreateGroupChildrenResponseDtoToGroupChildren(request);
        GroupChildren savedGroupChildren = this.groupChildrenService.save(groupChildren);
        CreateGroupChildrenResponseDto response =
                GroupChildrenMapper.mapFromGroupChildrenToCreateGroupChildrenResponseDto(savedGroupChildren);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateGroupChildrenResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateGroupChildrenRequestDto request){
        GroupChildren newGroupChildren = mapFromUpdateGroupChildrenResponseDtoToGroupChildren(request);
        this.groupChildrenService.updateById(id, newGroupChildren);
        UpdateGroupChildrenResponseDto response = mapFromGroupChildrentoUpdateGroupChildrenResponseDto(newGroupChildren);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteGroupChildrenDto> deleteGroupChildrenById(@PathVariable Long id) {
        if (this.groupChildrenService.findById(id).getChild().size() == 0) {
            this.groupChildrenService.deleteById(id);
            DeleteGroupChildrenDto response = mapFromGroupChildrenToDeleteGroupChildrenResponseDto(id);
            return ResponseEntity.ok(response);
        }
        DeleteGroupChildrenDto response = GroupChildrenMapper.NotDeleteGroupChildrenWithChild(id);
        return ResponseEntity.ok(response);
    }
}
