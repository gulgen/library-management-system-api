package com.patika.Library.Management.System.API.business.abstracts;

import com.patika.Library.Management.System.API.dto.request.book.BookSaveRequest;
import com.patika.Library.Management.System.API.dto.request.book.BookUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.BookResponse;
import com.patika.Library.Management.System.API.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    BookResponse saveBook(BookSaveRequest bookSaveRequest);

    Book save(Book book);

    Book get(int id);

    Book update(Book book);

    BookResponse update(int id, BookUpdateRequest request);

    boolean delete(int id);

    Page<Book> cursor(int page, int pageSize);

    BookResponse getBookResponse(Book book);
}
