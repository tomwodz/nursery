package pl.tomwodz.nursery.infrastructure.groupchildren.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.domain.groupchildren.dto.DeleteGroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/groupchildren")
public class GroupChildrenRestController {

    private final GroupChildrenFacade groupChildrenFacade;
    private final ChildFacade childFacade;

    @GetMapping
    public ResponseEntity<List<GroupChildrenResponseDto>> getAllChildren() {
        List<GroupChildrenResponseDto> allGroupsChildren = this.groupChildrenFacade.findAllGroupsChildren();
        return ResponseEntity.ok(allGroupsChildren);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GroupChildrenResponseDto> getChildById(@PathVariable Long id){
        return ResponseEntity.ok(this.groupChildrenFacade.findGroupChildrenById(id));
    }

    @PostMapping(path = "/")
    public ResponseEntity<GroupChildrenResponseDto> postGroupChildren(
            @RequestBody @Valid GroupChildrenRequestDto request) {
        return ResponseEntity.ok(this.groupChildrenFacade.saveGroupChildren(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupChildrenResponseDto> update(@PathVariable Long id,
                                                                 @RequestBody @Valid GroupChildrenRequestDto request){
        return ResponseEntity.ok(this.groupChildrenFacade.updateGroupChildren(id, request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteGroupChildrenResponseDto> deleteGroupChildrenById(@PathVariable Long id) {
            return ResponseEntity.ok(this.groupChildrenFacade.deleteGroupChildren(id));
    }

}
