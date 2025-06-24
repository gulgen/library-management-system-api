package com.patika.Library.Management.System.API.business.abstracts;

import com.patika.Library.Management.System.API.dto.request.bookborrowing.BookBorrowingSaveRequest;
import com.patika.Library.Management.System.API.dto.request.bookborrowing.BookBorrowingUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.BookBorrowingResponse;
import com.patika.Library.Management.System.API.entities.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBookBorrowingService {
    BookBorrowingResponse save(BookBorrowingSaveRequest bookBorrowingSaveRequest);
    BookBorrowingResponse get(int id);
    BookBorrowingResponse update(BookBorrowingUpdateRequest borrowUpdateRequest);
    String delete(int id);
    Page<BookBorrowing> cursor(int page, int pageSize);
}
