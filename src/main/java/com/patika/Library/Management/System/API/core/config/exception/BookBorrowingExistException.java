package com.patika.Library.Management.System.API.core.config.exception;

public class BookBorrowingExistException extends RuntimeException {
    public BookBorrowingExistException(String message) {
        super(message);
    }
}
