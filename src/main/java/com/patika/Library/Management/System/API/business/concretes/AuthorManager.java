package com.patika.Library.Management.System.API.business.concretes;

import com.patika.Library.Management.System.API.business.abstracts.IAuthorService;
import com.patika.Library.Management.System.API.core.config.exception.NotFoundException;
import com.patika.Library.Management.System.API.core.utils.Msg;
import com.patika.Library.Management.System.API.dao.AuthorRepository;
import com.patika.Library.Management.System.API.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class AuthorManager implements IAuthorService {
    private final AuthorRepository authorRepo;

    public AuthorManager(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.save(author);
    }

    @Override
    public boolean delete(int id) {
        this.authorRepo.delete(this.get(id));
        return true;
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page,pageSize);
        return  this.authorRepo.findAll(pageable);
    }
}
