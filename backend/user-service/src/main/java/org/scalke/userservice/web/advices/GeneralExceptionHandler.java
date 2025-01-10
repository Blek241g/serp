package org.scalke.userservice.web.advices;

import org.scalke.exceptions.AlreadyExistException;
import org.scalke.exceptions.DoesNotExistException;
import org.scalke.exceptions.NotFoundException;
import org.scalke.models.ErrorDetails;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .message(e.getMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoesNotExistException.class)
    public ResponseEntity<ErrorDetails> handleDoesNotExistException(DoesNotExistException e) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .message(e.getMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleAlreadyExistException(AlreadyExistException e) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .message(e.getMessage())
                .build(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoleServiceLogicException.class)
    public ResponseEntity<ErrorDetails> handleRoleServiceLogicException(RoleServiceLogicException e) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .message(e.getMessage())
                .build(),
                e.status);
    }
}
