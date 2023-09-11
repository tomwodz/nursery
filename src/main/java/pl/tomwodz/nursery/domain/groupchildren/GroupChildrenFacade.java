package pl.tomwodz.nursery.domain.groupchildren;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.groupchildren.dto.DeleteGroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotFoundException;

import java.util.List;

import static pl.tomwodz.nursery.domain.groupchildren.GroupChildrenMapper.*;

@AllArgsConstructor
@Transactional
public class GroupChildrenFacade {

    private final GroupChildrenRepository groupChildrenRepository;
    private final ValidatorFacade validatorFacade;
    private final GroupChildrenService groupChildrenService;

    public List<GroupChildrenResponseDto> findAllGroupsChildren(){
        return this.groupChildrenRepository.findAll()
                .stream()
                .map(GroupChildrenMapper::mapFromGroupChildrenToGroupChildrenResponseDto)
                .toList();
    }

    public GroupChildrenResponseDto findGroupChildrenById(Long id) {
        GroupChildren groupChildrenFounded = this.groupChildrenService.findGroupChildrenById(id);
        return GroupChildrenMapper.mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenFounded);
    }

    public GroupChildrenResponseDto findGroupChildrenByName(String name) {
        return this.groupChildrenRepository.findByName(name)
                .map(GroupChildrenMapper::mapFromGroupChildrenToGroupChildrenResponseDto)
                .orElseThrow(()-> new GroupChildrenNotFoundException("not found group children name: " + name));
    }

    public GroupChildrenResponseDto saveGroupChildren(GroupChildrenRequestDto groupChildrenRequestDto){
        validatorFacade.validationGroupChildren(groupChildrenRequestDto);
        final GroupChildren groupChildrenSaved = this.groupChildrenService.saveGroupChildren(groupChildrenRequestDto);
        return mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenSaved);
    }

    public GroupChildrenResponseDto updateGroupChildren(Long id, GroupChildrenRequestDto groupChildrenRequestDto){
        validatorFacade.validationGroupChildren(groupChildrenRequestDto);
        final GroupChildren groupChildrenSaved = this.groupChildrenService.updateGroupChildren(id, groupChildrenRequestDto);
        return mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenSaved);
    }

    public DeleteGroupChildrenResponseDto deleteGroupChildren(Long id) {
        this.groupChildrenService.deleteGroupChildren(id);
        return DeleteGroupChildrenResponseDto.builder()
                .message("You deleted group children with id: " + id)
                .status(HttpStatus.OK)
                .build();
    }

}
