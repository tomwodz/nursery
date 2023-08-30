package pl.tomwodz.nursery.exception.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.controllers.rest.GroupChildrenRestController;
import pl.tomwodz.nursery.controllers.view.GroupChildrenViewController;
import pl.tomwodz.nursery.exception.handler.response.ErrorGroupChildrenResponseDto;
import pl.tomwodz.nursery.exception.GroupChildrenNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = {
                GroupChildrenViewController.class,
                GroupChildrenRestController.class})
@Log4j2
public class GroupChildrenErrorHandler {

    @ExceptionHandler(GroupChildrenNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorGroupChildrenResponseDto handleException(GroupChildrenNotFoundException exception){
        log.warn("GroupChildrenNotFoundException error while accessing group children");
        return new ErrorGroupChildrenResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }
}
