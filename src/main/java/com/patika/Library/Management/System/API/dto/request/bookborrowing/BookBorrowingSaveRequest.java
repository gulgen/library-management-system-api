package com.patika.Library.Management.System.API.dto.request.bookborrowing;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingSaveRequest {
    @NotNull
    private String borrowerName;
    @NotNull
    private String borrowerEmail;
    @NotNull
    private LocalDate borrowingDate;
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private long bookId;
}
