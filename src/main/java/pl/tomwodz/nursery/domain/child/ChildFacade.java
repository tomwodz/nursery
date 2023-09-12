package pl.tomwodz.nursery.domain.child;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.domain.child.dto.DeleteChildResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.child.controller.error.ChildNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@AllArgsConstructor
@Transactional
public class ChildFacade {

    private final ChildRepository childRepository;
    private final ValidatorFacade validatorFacade;
    private final ChildFactory childFactory;

    public Long getQuantityChildrenByGroupId(Long id){
        return this.childRepository.findAllByGroupChildren_Id(id)
                .stream()
                .count();
    }

    public List<ChildResponseDto> findAllChildrenByGroupChildrenId(Long id){
        return this.childRepository.findAllByGroupChildren_Id(id)
                .stream()
                .map(ChildMapper::mapFromChildToChildResponseDto)
                .toList();
    }

    public List<ChildResponseDto> findAllChildren(){
        return this.childRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(ChildMapper::mapFromChildToChildResponseDto)
                .toList();
    }

    public ChildResponseDto findChildById(Long id) {
        return this.childRepository.findById(id)
                .map(ChildMapper::mapFromChildToChildResponseDto)
                .orElseThrow(()-> new ChildNotFoundException("not found child id: " + id));
    }

    public ChildResponseDto saveChild(ChildRequestDto childRequestDto){
        validatorFacade.validationChild(childRequestDto);
        Child child = childFactory.mapFromChildReguestDtoToChild(childRequestDto);
        Child savedChild = this.childRepository.save(child);
        return ChildMapper.mapFromChildToChildResponseDto(savedChild);
    }

    public ChildResponseDto updateChild(Long id, ChildRequestDto childRequestDto){
        validatorFacade.validationChild(childRequestDto);
        this.existsById(id);
        Child child = childFactory.mapFromChildReguestDtoToChild(childRequestDto);
        child.setId(id);
        Child savedChild = this.childRepository.save(child);
        return ChildMapper.mapFromChildToChildResponseDto(savedChild);
    }

    public DeleteChildResponseDto deleteChildById(Long id) {
        this.existsById(id);
        this.childRepository.deleteById(id);
        return DeleteChildResponseDto.builder()
                .message("You deleted child with id: " + id)
                .status(HttpStatus.OK)
                .build();
    }

    public Map <Long, Long> getQuantityChildrenByGroups(){
        return this.childRepository.findAll()
                .stream()
                .collect(groupingBy(Child -> Child.getGroupChildren().getId(), counting()));
    }

    private void existsById(Long id){
        if(!this.childRepository.existsById(id)){
            throw new ChildNotFoundException("not found child id: " + id);
        }
    }

    public Long getQuantityChildrenByUserId(Long id) {
        return this.childRepository.findAllByParent_Id(id)
                .stream()
                .count();
    }

    public List<ChildResponseDto> findChildrenByUserId(Long id) {
        return this.childRepository.findAllByParent_Id(id)
                .stream()
                .map(ChildMapper::mapFromChildToChildResponseDto)
                .toList();
    }

    public boolean checkExistenceOfParentChildRelationship(Long idChild, Long idParent){
        Optional<Child> childBox = this.childRepository.findById(idChild);
        if(childBox.isEmpty()){
               throw new ChildNotFoundException("not found child id: " + idChild);}
        if (childBox.get().getParent().getId() == idParent){
            return true;
        }
        return false;
    }
}
