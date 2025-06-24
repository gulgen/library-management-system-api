package com.patika.Library.Management.System.API.dao;

import com.patika.Library.Management.System.API.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
