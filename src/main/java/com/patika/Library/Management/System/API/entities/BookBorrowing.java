package com.patika.Library.Management.System.API.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "book_borrowings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id", unique = true)
    private int id;

    @Column(name = "book_borrower_name")
    private String borrowerName;

    @Column(name = "book_borrowing_date", nullable = false)
    private LocalDate borrowingDate;

    @Column(name = "book_return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

}
