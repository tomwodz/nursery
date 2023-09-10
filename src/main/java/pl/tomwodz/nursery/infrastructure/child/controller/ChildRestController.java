package pl.tomwodz.nursery.infrastructure.child.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.child.dto.DeleteChildResponseDto;
import pl.tomwodz.nursery.domain.child.dto.GetAllChildrenResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.GroupChildrenFacade;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/child")
public class ChildRestController {

    private final GroupChildrenFacade groupChildrenFacade;
    private final ChildFacade childFacade;

    @GetMapping
    public ResponseEntity<GetAllChildrenResponseDto> getAllChildren() {
        return ResponseEntity.ok(
                GetAllChildrenResponseDto.builder()
                .children(this.childFacade.findAllChildren())
                .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ChildResponseDto> getChildById(@PathVariable Long id){;
        return ResponseEntity.ok(childFacade.findChildById(id));
    }

    @PostMapping(path = "/")
    public ResponseEntity<ChildResponseDto> addChild(
            @RequestBody @Valid ChildRequestDto childRequestDto) {
        try {
            this.groupChildrenFacade.findGroupChildrenById(childRequestDto.groupChildren_id());
            ChildResponseDto childSaved = this.childFacade.saveChild(childRequestDto);
            return ResponseEntity.ok(childSaved);
        }
        catch (GroupChildrenNotFoundException e){
            ChildRequestDto newChildRequestDto = ChildRequestDto.builder()
                    .name(childRequestDto.name())
                    .surname(childRequestDto.surname())
                    .dayBirth(childRequestDto.dayBirth())
                    .user_id(childRequestDto.user_id())
                    .groupChildren_id(1L)
                    .build();
                    ChildResponseDto childSaved = this.childFacade.saveChild(newChildRequestDto);
            return ResponseEntity.ok(childSaved);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ChildResponseDto> update(@PathVariable Long id,
                                                           @RequestBody @Valid ChildRequestDto request){
        return ResponseEntity.ok(this.childFacade.updateChild(id, request));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteChildResponseDto> deleteChildById(@PathVariable Long id) {
        return ResponseEntity.ok(this.childFacade.deleteChildById(id));
    }

}
