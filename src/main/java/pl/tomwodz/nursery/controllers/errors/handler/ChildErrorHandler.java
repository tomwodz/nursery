package pl.tomwodz.nursery.controllers.errors.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.controllers.ChildViewController;
import pl.tomwodz.nursery.controllers.errors.ChildNotFoundException;
import pl.tomwodz.nursery.controllers.errors.handler.response.ErrorChildResponseDto;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = ChildViewController.class)
@Log4j2
public class ChildErrorHandler {

    @ExceptionHandler(ChildNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorChildResponseDto handleException(ChildNotFoundException exception){
        log.warn("ChildNotExistException error while accessing child");
        return new ErrorChildResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }
}