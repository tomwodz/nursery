package pl.tomwodz.nursery.domain.groupchildren;

import lombok.AllArgsConstructor;
import pl.tomwodz.nursery.domain.child.ChildFacade;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenRequestDto;
import pl.tomwodz.nursery.domain.groupchildren.dto.GroupChildrenResponseDto;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotDeleteException;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.error.GroupChildrenNotFoundException;

import java.util.List;

import static pl.tomwodz.nursery.domain.groupchildren.GroupChildrenMapper.*;

@AllArgsConstructor
class GroupChildrenService {

    private final GroupChildrenRepository groupChildrenRepository;
    private final ChildFacade childFacade;

    void deleteGroupChildren(Long id) {
        this.existsById(id);
        if(this.childFacade.getQuantityChildrenByGroupId(id)!=0){
            throw new GroupChildrenNotDeleteException("not delete, group children must be empty");
        }
        this.groupChildrenRepository.deleteById(id);
    }

    GroupChildren updateGroupChildren(Long id, GroupChildrenRequestDto groupChildrenRequestDto){
        this.existsById(id);
        final GroupChildren groupChildren = mapFromUpdateGroupChildrenRequestDtoToGroupChildren(id, groupChildrenRequestDto);
        return this.groupChildrenRepository.save(groupChildren);
    }

    GroupChildren saveGroupChildren(GroupChildrenRequestDto groupChildrenRequestDto){
        final GroupChildren groupChildren = mapFromGroupChildrenRequestDtoToGroupChildren(groupChildrenRequestDto);
        return this.groupChildrenRepository.save(groupChildren);
    }

    GroupChildren findGroupChildrenById(Long id) {
        return this.groupChildrenRepository.findById(id)
                .orElseThrow(()-> new GroupChildrenNotFoundException("not found group children id: " + id));
    }

    private void existsById(Long id){
        if(!this.groupChildrenRepository.existsById(id)){
            throw new GroupChildrenNotFoundException("not found group children id: " + id);
        }
    }

}
