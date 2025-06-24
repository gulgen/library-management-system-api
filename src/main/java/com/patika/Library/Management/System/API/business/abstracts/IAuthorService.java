package com.patika.Library.Management.System.API.business.abstracts;

import com.patika.Library.Management.System.API.entities.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    Author save(Author author);

    Author get(int id);

    Author update(Author author);

    boolean delete(int id);

    Page<Author> cursor(int page, int pageSize);
}
