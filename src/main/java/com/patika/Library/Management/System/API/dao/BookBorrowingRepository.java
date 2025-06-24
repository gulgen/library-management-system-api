package com.patika.Library.Management.System.API.dao;

import com.patika.Library.Management.System.API.entities.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepository extends JpaRepository<BookBorrowing, Integer> {
}
