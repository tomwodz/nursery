package pl.tomwodz.nursery.apivalidation;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.nursery.controllers.rest.ChildRestController;
import pl.tomwodz.nursery.controllers.rest.GroupChildrenRestController;
import pl.tomwodz.nursery.controllers.rest.InformationRestController;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = {
        GroupChildrenRestController.class,
        InformationRestController.class,
        ChildRestController.class})
public class ApiValidationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiValidationErrorResponseDto handlerValidationException(MethodArgumentNotValidException exception){
       List<String> message = getErrorsFromException(exception);
        return new ApiValidationErrorResponseDto(message, HttpStatus.BAD_REQUEST);
    }

    private List<String> getErrorsFromException(MethodArgumentNotValidException exception){
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
