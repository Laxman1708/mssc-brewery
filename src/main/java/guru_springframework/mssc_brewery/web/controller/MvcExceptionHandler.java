package guru_springframework.mssc_brewery.web.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validateRequest(ConstraintViolationException e) {

        List<String> errorList = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(error -> errorList.add(error.getPropertyPath() + ":" + error.getMessage()));
        System.out.println("no. of exceptions: " +e.getConstraintViolations().size());
        System.out.println("below are the exceptions in your input: " +errorList);
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException e) {

        return new ResponseEntity<>(e.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
