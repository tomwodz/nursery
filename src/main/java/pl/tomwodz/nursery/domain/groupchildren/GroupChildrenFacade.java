package pl.tomwodz.nursery.domain.groupchildren;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.groupchildren.dto.DeleteGroupChildrenResponseDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotFoundException;
import pl.tomwodz.nursery.model.GroupChildren;

import java.util.List;

import static pl.tomwodz.nursery.domain.groupchildren.GroupChildrenMapper.*;
import static pl.tomwodz.nursery.domain.groupchildren.GroupChildrenMapper.mapFromGroupChildrenRequestDtoToGroupChildren;
import static pl.tomwodz.nursery.domain.groupchildren.GroupChildrenMapper.mapFromGroupChildrenToGroupChildrenResponseDto;

@AllArgsConstructor
@Transactional
public class GroupChildrenFacade {

    private final GroupChildrenRepository groupChildrenRepository;
    public List<GroupChildrenResponseDto> findAllGroupsChildren(){
        return this.groupChildrenRepository.findAll()
                .stream()
                .map(GroupChildrenMapper::mapFromGroupChildrenToGroupChildrenResponseDto)
                .toList();
    }

    public GroupChildrenResponseDto findGroupChildrenById(Long id) {
        return this.groupChildrenRepository.findById(id)
                .map(GroupChildrenMapper::mapFromGroupChildrenToGroupChildrenResponseDto)
                .orElseThrow(()-> new GroupChildrenNotFoundException("not found group children id: " + id));
    }

    public GroupChildrenResponseDto saveGroupChildren(GroupChildrenRequestDto groupChildrenRequestDto){
        GroupChildrenValidator.validatorGroupChildren(groupChildrenRequestDto);
        final GroupChildren groupChildren = mapFromGroupChildrenRequestDtoToGroupChildren(groupChildrenRequestDto);
        final GroupChildren groupChildrenSaved = this.groupChildrenRepository.save(groupChildren);
        return mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenSaved);
    }

    public GroupChildrenResponseDto updateGroupChildren(Long id, GroupChildrenRequestDto groupChildrenRequestDto){
        this.existsById(id);
        final GroupChildren groupChildren = mapFromUpdateGroupChildrenRequestDtoToGroupChildren(id, groupChildrenRequestDto);
        final GroupChildren groupChildrenSaved = this.groupChildrenRepository.save(groupChildren);
        return mapFromGroupChildrenToGroupChildrenResponseDto(groupChildrenSaved);
    }

    public DeleteGroupChildrenResponseDto deleteGroupChildren(Long id) {
        this.existsById(id);
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
