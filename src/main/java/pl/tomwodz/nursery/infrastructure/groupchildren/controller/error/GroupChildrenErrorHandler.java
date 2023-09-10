package pl.tomwodz.nursery.infrastructure.groupchildren.controller.error;

import jakarta.validation.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.infrastructure.child.controller.ChildRestController;
import pl.tomwodz.nursery.infrastructure.information.controller.error.ErrorInformationResponseDto;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.GroupChildrenRestController;
import pl.tomwodz.nursery.infrastructure.groupchildren.controller.GroupChildrenViewController;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = {
                GroupChildrenViewController.class,
                GroupChildrenRestController.class,
                ChildRestController.class})
@Log4j2
public class GroupChildrenErrorHandler {

    @ExceptionHandler(GroupChildrenNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorGroupChildrenResponseDto handleException(GroupChildrenNotFoundException exception){
        log.warn("GroupChildrenNotFoundException error while accessing group children");
        return new ErrorGroupChildrenResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInformationResponseDto handleException(ValidationException exception){
        log.warn("ValidationException error while accessing user");
        return new ErrorInformationResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }
}
