package ru.agiletech.teammate.service.presentation.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.agiletech.teammate.service.infrastructure.persistence.RepositoryAccessException;
import ru.agiletech.teammate.service.infrastructure.persistence.TeammateNotFoundException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    private ResponseEntity<String> catchValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors =  bindingResult.getAllErrors();

        StringBuilder reason = new StringBuilder();

        errors.forEach(error -> reason.append(error.getDefaultMessage())
                .append(";"));

        log.error(reason.toString());

        return new ResponseEntity<>(reason.toString(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    private ResponseEntity<String> catchUnsupportedOperationException(UnsupportedOperationException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepositoryAccessException.class)
    private ResponseEntity<String> catchInfrastructureExceptions(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TeammateNotFoundException.class)
    private ResponseEntity<String> catchNotFoundException(TeammateNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }
}
