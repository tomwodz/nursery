package pl.tomwodz.nursery.domain.groupchildren;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.groupchildren.dto.DeleteGroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotDeleteException;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotFoundException;

import java.util.List;

import static pl.tomwodz.nursery.domain.groupchildren.GroupChildrenMapper.*;

@AllArgsConstructor
@Transactional
public class GroupChildrenFacade {

    private final GroupChildrenRepository groupChildrenRepository;
    private final ValidatorFacade validatorFacade;
    private final GroupChildrenFactory groupChildrenFactory;
    private final ChildFacade childFacade;

    public List<GroupChildrenResponseDto> findAllGroupsChildren(){
        return this.groupChildrenRepository.findAll()
                .stream()
                .map(GroupChildrenMapper::mapFromGroupChildrenToGroupChildrenResponseDto)
                .toList();
    }

    public GroupChildrenResponseDto findGroupChildrenById(Long id) {
        GroupChildren groupChildrenFounded = this.groupChildrenRepository.findById(id)
                .orElseThrow(()-> new GroupChildrenNotFoundException("not found group children id: " + id));;
        return GroupChildrenMapper.mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenFounded);
    }

    public GroupChildrenResponseDto findGroupChildrenByName(String name) {
        return this.groupChildrenRepository.findByName(name)
                .map(GroupChildrenMapper::mapFromGroupChildrenToGroupChildrenResponseDto)
                .orElseThrow(()-> new GroupChildrenNotFoundException("not found group children name: " + name));
    }

    public GroupChildrenResponseDto saveGroupChildren(GroupChildrenRequestDto groupChildrenRequestDto){
        validatorFacade.validationGroupChildren(groupChildrenRequestDto);
        GroupChildren groupChildren = groupChildrenFactory.mapFromGroupChildrenRequestDtoToGroupChildren(groupChildrenRequestDto);
        GroupChildren groupChildrenSaved = this.groupChildrenRepository.save(groupChildren);
        return mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenSaved);
    }

    public GroupChildrenResponseDto updateGroupChildren(Long id, GroupChildrenRequestDto groupChildrenRequestDto){
        this.existsById(id);
        validatorFacade.validationGroupChildren(groupChildrenRequestDto);
        GroupChildren groupChildren = groupChildrenFactory.mapFromUpdateGroupChildrenRequestDtoToGroupChildren(id, groupChildrenRequestDto);
        GroupChildren groupChildrenSaved = this.groupChildrenRepository.save(groupChildren);
        return mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenSaved);
    }

    public DeleteGroupChildrenResponseDto deleteGroupChildren(Long id) {
        this.existsById(id);
        if(this.childFacade.getQuantityChildrenByGroupId(id)!=0){
            throw new GroupChildrenNotDeleteException("not delete, group children must be empty");
        }
        this.groupChildrenRepository.deleteById(id);
        return DeleteGroupChildrenResponseDto.builder()
                .message("You deleted group children with id: " + id)
                .status(HttpStatus.OK)
                .build();
    }

    private void existsById(Long id){
        if(!this.groupChildrenRepository.existsById(id)){
            throw new GroupChildrenNotFoundException("not found group children id: " + id);
        }
    }

}
