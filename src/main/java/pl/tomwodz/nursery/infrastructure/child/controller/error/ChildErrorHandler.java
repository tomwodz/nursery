package pl.tomwodz.nursery.infrastructure.child.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.infrastructure.child.controller.ChildRestController;
import pl.tomwodz.nursery.infrastructure.child.controller.ChildViewController;
import pl.tomwodz.nursery.infrastructure.validator.error.ChildValidationException;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = {
        ChildViewController.class,
        ChildRestController.class})
@Log4j2
public class ChildErrorHandler {

    @ExceptionHandler(ChildNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorChildResponseDto handleException(ChildNotFoundException exception){
        log.warn("ChildNotExistException error while accessing child");
        return new ErrorChildResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }

    @ExceptionHandler(ChildValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorChildResponseDto handleException(ChildValidationException exception){
        log.warn("ChildValidationException");
        return new ErrorChildResponseDto(exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now());
    }

}
