package pl.tomwodz.nursery.infrastructure.information.controller.error;

import jakarta.validation.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.infrastructure.information.controller.InformationRestController;
import pl.tomwodz.nursery.infrastructure.information.controller.InformationViewController;

import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = {InformationViewController.class,
                                    InformationRestController.class})
@Log4j2
public class InformationErrorHandler {

    @ExceptionHandler(InformationNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInformationResponseDto handleException(InformationNotFoundException exception){
        log.warn("InformationNotFoundException error while accessing user");
        return new ErrorInformationResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInformationResponseDto handleException(ValidationException exception){
        log.warn("ValidationException error while accessing user");
        return new ErrorInformationResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }
}
