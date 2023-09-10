package pl.tomwodz.nursery.domain.child;

import pl.tomwodz.nursery.domain.child.dto.ChildRequestDto;
import pl.tomwodz.nursery.domain.child.dto.ChildResponseDto;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.model.GroupChildren;
import pl.tomwodz.nursery.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChildMapper {


    public static ChildResponseDto mapFromChildToChildResponseDto(Child child) {
        return ChildResponseDto.builder()
                .id(child.getId())
                .name(child.getName())
                .surname(child.getSurname())
                .groupChildren_id(child.getGroupChildren().getId())
                .dayBirth(child.getDayBirth().toString())
                .user_id(child.getParent().getId())
                .build();
    }

    public static Child mapFromChildReguestDtoToChild(ChildRequestDto dto) {
        return Child.builder()
                .name(dto.name())
                .surname(dto.surname())
                .groupChildren(new GroupChildren(dto.groupChildren_id()))
                .dayBirth(LocalDate.parse(dto.dayBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .parent(new User(dto.user_id()))
                .build();
    }

    public static ChildRequestDto mapFromChildToChildRequestDto(Child child) {
        return ChildRequestDto.builder()
                .name(child.getName())
                .surname(child.getSurname())
                .groupChildren_id(child.getGroupChildren().getId())
                .dayBirth(child.getDayBirth().toString())
                .user_id(child.getParent().getId())
                .build();
    }

    public static Child mapFromUpdateChildRequestDtoToChild(Long id, ChildRequestDto dto) {
        return Child.builder()
                .id(id)
                .name(dto.name())
                .surname(dto.surname())
                .groupChildren(new GroupChildren(dto.groupChildren_id()))
                .dayBirth(LocalDate.parse(dto.dayBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .parent(new User(dto.user_id()))
                .build();
    }

}
