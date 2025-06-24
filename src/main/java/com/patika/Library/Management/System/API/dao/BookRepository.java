package com.patika.Library.Management.System.API.dao;

import com.patika.Library.Management.System.API.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByCategoriesId(Long id);
}
