package com.patika.Library.Management.System.API.business.concretes;

import com.patika.Library.Management.System.API.business.abstracts.IPublisherService;
import com.patika.Library.Management.System.API.core.config.exception.NotFoundException;
import com.patika.Library.Management.System.API.core.utils.Msg;
import com.patika.Library.Management.System.API.dao.PublisherRepository;
import com.patika.Library.Management.System.API.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class PublisherManager implements IPublisherService {
    private final PublisherRepository publisherRepo;

    public PublisherManager(PublisherRepository publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public String delete(int id) {
        this.publisherRepo.delete(this.get(id));
        return "";
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.publisherRepo.findAll(pageable);
    }
}
