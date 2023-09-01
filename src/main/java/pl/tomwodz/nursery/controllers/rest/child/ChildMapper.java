package pl.tomwodz.nursery.controllers.rest.child;

import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.controllers.rest.child.request.UpdateChildRequestDto;
import pl.tomwodz.nursery.controllers.rest.child.response.UpdateChildResponseDto;
import pl.tomwodz.nursery.controllers.rest.child.request.CreateChildRequestDto;
import pl.tomwodz.nursery.controllers.rest.child.response.*;
import pl.tomwodz.nursery.model.Child;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ChildMapper {

    public static ChildDto mapFromChildToChildDto(Child child) {
        return new ChildDto(
                child.getId(),
                child.getName(),
                child.getSurname(),
                child.getGroupChildren().getId(),
                child.getDayOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                child.getParent().getId()
        );
    }

    public static GetAllChildrenResponseDto mapFromChildToGetAllChildrenResponstDto(List<Child> allChildren) {
        List<ChildDto> childrenDto = allChildren.stream()
                .map(ChildMapper::mapFromChildToChildDto)
                .toList();
        return new GetAllChildrenResponseDto(childrenDto);
    }

    public static GetChildDto mapFromChildToGetChildDto(Child child) {
        ChildDto childDto = mapFromChildToChildDto(child);
        return new GetChildDto(childDto);
    }

    public static DeleteChildResponseDto mapFromChildToDeleteChildResponseDto(Long id) {
        return new DeleteChildResponseDto("You deleted child with id: " + id, HttpStatus.OK);
    }

    public static Child mapFromCreateChildRequestDtoToChild(CreateChildRequestDto dto) {
        Child child = new Child();
        child.setName(dto.name());
        child.setSurname(dto.surname());
        try {
        child.setDayOfBirth(LocalDate.parse(dto.dayBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));}
        catch (DateTimeParseException e){
            child.setDayOfBirth(null); //TODO
        }
        return child;
    }

    public static CreateChildResponseDto mapFromChildToCreateChildResponseDto(Child savedChild) {
        ChildDto childDto = mapFromChildToChildDto(savedChild);
        return new CreateChildResponseDto(childDto);
    }

    public static Child mapFromUpdateChildRequestDtoToChild(UpdateChildRequestDto dto) {
        String newName = dto.name();
        String newSurname = dto.surname();
        LocalDate newDayBirth = LocalDate.parse(dto.dayBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new Child(newName, newSurname, newDayBirth);
    }

    public static UpdateChildResponseDto mapFromChildToUpdateChildResponseDto(Child newChild) {
        ChildDto childDto = mapFromChildToChildDto(newChild);
        return new UpdateChildResponseDto(childDto);
    }
}
