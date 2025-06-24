package com.patika.Library.Management.System.API.business.concretes;

import com.patika.Library.Management.System.API.business.abstracts.IAuthorService;
import com.patika.Library.Management.System.API.business.abstracts.IBookService;
import com.patika.Library.Management.System.API.business.abstracts.ICategoryService;
import com.patika.Library.Management.System.API.business.abstracts.IPublisherService;
import com.patika.Library.Management.System.API.core.config.exception.NotFoundException;
import com.patika.Library.Management.System.API.core.config.modelMapper.IModelMapperService;
import com.patika.Library.Management.System.API.core.utils.Msg;
import com.patika.Library.Management.System.API.dao.BookRepository;
import com.patika.Library.Management.System.API.dto.request.book.BookSaveRequest;
import com.patika.Library.Management.System.API.dto.request.book.BookUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.BookResponse;
import com.patika.Library.Management.System.API.entities.Author;
import com.patika.Library.Management.System.API.entities.Book;
import com.patika.Library.Management.System.API.entities.Category;
import com.patika.Library.Management.System.API.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookManager implements IBookService {
    private final IModelMapperService modelMapper;
    private final BookRepository bookRepo;
    private final IAuthorService authorService;
    private final ICategoryService categoryService;
    private final IPublisherService publisherService;

    public BookManager(IModelMapperService modelMapper, BookRepository bookRepo, IAuthorService authorService, ICategoryService categoryService, IPublisherService publisherService) {
        this.modelMapper = modelMapper;
        this.bookRepo = bookRepo;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @Override
    public BookResponse saveBook(BookSaveRequest bookSaveRequest) {
        Book book = modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = authorService.get(bookSaveRequest.getAuthorId());
        book.setAuthor(author);

        Publisher publisher = publisherService.get(bookSaveRequest.getPublisherId());
        book.setPublisher(publisher);

        List<Category> categories = categoryService.getAllByIds(bookSaveRequest.getCategoryIds());
        book.setCategories((Set<Category>) categories);

        this.save(book);

        return getBookResponse(book);
    }

    @Override
    public BookResponse update(int id, BookUpdateRequest request) {

        Book book = this.get(id);


        book.setName(request.getName());
        book.setStock(request.getStock());
        book.setPublicationYear(request.getPublicationYear());


        Author author = authorService.get(request.getAuthorId());
        book.setAuthor(author);

        Publisher publisher = publisherService.get(request.getPublisherId());
        book.setPublisher(publisher);

        List<Category> categories = categoryService.getAllByIds(request.getCategoryIds());
        book.setCategories((Set<Category>) categories);


        this.save(book);

        return getBookResponse(book);
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book get(int id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId());
        return this.save(book);
    }

    @Override
    public boolean delete(int id) {
        this.bookRepo.delete(this.get(id));
        return true;
    }
    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookRepo.findAll(pageable);
    }

    @Override
    public BookResponse getBookResponse(Book book) {
        List<String> categoryNames = book.getCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());

        BookResponse response = new BookResponse();
        response.setName(book.getName());
        response.setStock(book.getStock());
        response.setAuthorName(book.getAuthor().getName());
        response.setPublisherName(book.getPublisher().getName());
        response.setCategories(categoryNames);
        response.setPublicationYear(book.getPublicationYear());
        return response;
    }
}
