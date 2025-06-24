package com.patika.Library.Management.System.API.core.config;

import com.patika.Library.Management.System.API.core.config.exception.BookBorrowingExistException;
import com.patika.Library.Management.System.API.core.config.exception.BookUnavailableException;
import com.patika.Library.Management.System.API.core.config.exception.NotFoundException;
import com.patika.Library.Management.System.API.core.result.Result;
import com.patika.Library.Management.System.API.core.result.ResultData;
import com.patika.Library.Management.System.API.core.utils.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(ResultHelper.validationError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookBorrowingExistException.class)
    public ResponseEntity<Result> handleBookBorrowingExistException(BookBorrowingExistException e) {
        return new ResponseEntity<>(ResultHelper.badRequestError(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookUnavailableException.class)
    public ResponseEntity<Result> handleBookBorrowingExistException(BookUnavailableException e) {
        return new ResponseEntity<>(ResultHelper.badRequestError(e.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
