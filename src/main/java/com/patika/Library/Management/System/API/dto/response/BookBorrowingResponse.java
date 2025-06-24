package com.patika.Library.Management.System.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {
    private int id;

    private String borrowerName;

    private LocalDate borrowingDate;

    private String borrowerEmail;

    private LocalDate returnDate;

    private String bookName;
}
